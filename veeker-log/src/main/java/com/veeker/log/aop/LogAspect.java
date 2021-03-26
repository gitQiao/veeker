package com.veeker.log.aop;

import cn.hutool.core.collection.CollUtil;
import com.veeker.core.exceptions.BusinessException;
import com.veeker.log.domain.Log;
import com.veeker.log.enums.LogLevelType;
import com.veeker.log.event.LogConfigurerSupport;
import com.veeker.log.factory.LogConfigurationAdapter;
import com.veeker.log.properties.LogProperties;
import com.veeker.log.services.ILogOutputService;
import com.veeker.core.services.IOperatorService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * AOP 拦截配置
 *
 * @author ：qiaoliang
 */
@Aspect
@EnableConfigurationProperties({LogProperties.class})
@ConditionalOnProperty(prefix = "log", name = "enabled", havingValue = "true",matchIfMissing = true)
public class LogAspect {

    private final LogConfigurerSupport logConfigurerSupport;
    private final ILogOutputService logOutput;
    private Log logs = null;
    private final IOperatorService operatorService;
    private final LogProperties logProperties;

    public LogAspect(LogConfigurerSupport logConfigurerSupport,
                     LogConfigurationAdapter logConfigurationAdapter,
                     LogProperties logProperties,
                     IOperatorService operatorService) {
        this.operatorService = operatorService;
        this.logProperties = logProperties;
        this.logOutput = logConfigurationAdapter.getLogConfiguration
                (LogLevelType.valueOf(logProperties.getLevel()));
        this.logConfigurerSupport = logConfigurerSupport;
    }

    /**
     * Controller层切点 注解拦截
     */
    @Pointcut("@annotation(com.veeker.log.annotations.Log)")
    public void controllerAspect(){
    }

    /**
     * 方法调用前触发
     *
     * @author qiaoliang
     */
    @Before(value = "controllerAspect()")
    public void before(JoinPoint joinPoint) {
        HttpServletRequest request = this.getRequest();
        if(Objects.isNull(request) || this.isExclude(request)){
            return;
        }
        logs = new Log();
        logs.setCurrUserId(operatorService.operator());
        Method method = getMethod(joinPoint);
        com.veeker.log.annotations.Log annotation = method.getAnnotation(com.veeker.log.annotations.Log.class);
        logs.setMethodName(method.getName());
        logs.setBusinessType(annotation.businessType());
        logs.setDiscription(annotation.discription());
        logs.setOperatorType(operatorService.operatorType());
        logs.beforeCalling(request,joinPoint);
        logOutput.before(logs);
        logConfigurerSupport.before(logs);
    }


    /**
     * 方法调用后触发
     *
     * @author qiaoliang
     */
    @AfterReturning(returning = "ret", pointcut = "controllerAspect()")
    public void doAfterReturning(Object ret) {
        HttpServletRequest request = this.getRequest();
        if(Objects.isNull(request) || this.isExclude(request)){
            return;
        }
        logs.doAfterReturning(ret);
        logOutput.doAfterReturning(logs);
        logConfigurerSupport.doAfterReturning(logs);
    }


    /**
     * 异常通知：
     * 1. 在目标方法非正常结束，发生异常或者抛出异常时执行
     * 2. 在异常通知中设置异常信息，并将其保存
     *
     * @author qiaoliang
     * @param exception 异常信息
     */
    @AfterThrowing(value = "controllerAspect()", throwing = "exception")
    public void doAfterThrowing(Exception exception) {
        HttpServletRequest request = this.getRequest();
        if(Objects.isNull(request) || this.isExclude(request)){
            return;
        }
        logs.doAfterThrowing(exception);
        if(exception instanceof BusinessException){
            logOutput.businessLog(logs);
        }else {
            logOutput.doAfterThrowing(logs);
        }
        logConfigurerSupport.doAfterThrowing(logs);
    }


    /**
     * 获取注解信息
     *
     * @author ：qiaoliang
     * @param joinPoint : 请求
     * @return com.avocado.boot.starter.log.invalid.ControllerLog
     */
    private Method getMethod(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        //得到目标方法
        return signature.getMethod();
    }

    /**
     *  校验排除接口方式是否存在
     *
     * @author ：qiaoliang
     * @param request : 当前请求
     * @return boolean
     * @date 2021-03-23 15:30
     */
    private boolean isExclude(HttpServletRequest request){
        return CollUtil.isNotEmpty(logProperties.getExclude()) || logProperties.getExclude()
            .contains(request.getMethod());
    }

    private HttpServletRequest getRequest(){
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return Objects.nonNull(attributes)?attributes.getRequest():null;
    }

}
