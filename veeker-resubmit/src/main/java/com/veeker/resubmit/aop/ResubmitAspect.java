package com.veeker.resubmit.aop;


import cn.hutool.core.util.StrUtil;
import com.veeker.core.exceptions.BusinessException;
import com.veeker.core.services.IOperatorService;
import com.veeker.redis.utils.RedisUtil;
import com.veeker.resubmit.annotations.ResubmitAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;


/**
 * 重复提交切面
 *
 * @author ：qiaoliang
 * @date ：2020-09-11
 */
@Aspect
@Component
public class ResubmitAspect {

    /**重复提交验证缓存规则key**/
    public static final String INTEGRAL_RESUBMIT_USER_KEY = "resubmit:user:";

    private final IOperatorService operatorService;

    public ResubmitAspect(IOperatorService operatorService) {
        this.operatorService = operatorService;
    }


    @Pointcut("@annotation(com.veeker.resubmit.annotations.ResubmitAnnotation)")
    public void resubmit(){}

    /**
     * 方法执行前
     */
    @Before(value = "resubmit()")
    public void before(JoinPoint joinPoint){
        String currentUserId = (String) operatorService.operator();
        if(StrUtil.isNotBlank(currentUserId)){
            Method method = getMethod(joinPoint);
            ResubmitAnnotation annotation = method.getAnnotation(ResubmitAnnotation.class);
            String key = key(annotation.businessType(),method.getName(),currentUserId);
            Object createByRedis = RedisUtil.get(key);
            if(Objects.nonNull(createByRedis)){
                throw new BusinessException(annotation.errorMessage());
            }else {
                RedisUtil.set(key,annotation.resubmitTime(),currentUserId);
            }
        }
    }

    /**
     * 方法执行结束，不管是抛出异常或者正常退出都会执行
     */
    @After(value = "resubmit()")
    public void after(JoinPoint joinPoint){
        String currentUserId = (String) operatorService.operator();
        if(StrUtil.isNotBlank(currentUserId)){
            Method method = getMethod(joinPoint);
            ResubmitAnnotation annotation = method.getAnnotation(ResubmitAnnotation.class);
            RedisUtil.delete(key(annotation.businessType(),method.getName(),currentUserId));
        }
    }

    private String key(String businessType ,String methodName , String currentUserId){
        return INTEGRAL_RESUBMIT_USER_KEY + businessType +
                ":" + methodName + ":" + currentUserId;
    }

    private Method getMethod(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //得到目标方法
        return signature.getMethod();
    }

}
