package com.veeker.log.aop;

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

    public LogAspect(LogConfigurerSupport logConfigurerSupport,
                     LogConfigurationAdapter logConfigurationAdapter,
                     LogProperties logProperties,
                     IOperatorService operatorService) {
        this.operatorService = operatorService;
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
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        if(Objects.nonNull(attributes)){
            request = attributes.getRequest();
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



}
