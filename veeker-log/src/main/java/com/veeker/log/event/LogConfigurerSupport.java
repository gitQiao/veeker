package com.veeker.log.event;

import cn.hutool.core.bean.BeanUtil;
import com.veeker.log.domain.Log;
import com.veeker.log.services.IBaseLogOutputService;

/**
 * @author ：qiaoliang
 */
public class LogConfigurerSupport implements IBaseLogOutputService {

    private SystemLogEventPublisher systemLogEventPublisher;

    @Override
    public void before(Log log){
        SystemLogEvent systemLogEvent = new SystemLogEvent();
        BeanUtil.copyProperties(log,systemLogEvent);
        systemLogEventPublisher.asyncPublish(systemLogEvent);
    }

    @Override
    public void doAfterReturning(Log log){
        SystemSuccessLogEvent systemLogEvent = new SystemSuccessLogEvent();
        BeanUtil.copyProperties(log,systemLogEvent);
        systemLogEventPublisher.asyncPublish(systemLogEvent);
    }

    @Override
    public void doAfterThrowing(Log log){
        SystemErrorLogEvent systemLogEvent = new SystemErrorLogEvent();
        BeanUtil.copyProperties(log,systemLogEvent);
        systemLogEventPublisher.asyncPublish(systemLogEvent);
    }

    public void systemLogEventPublisher(SystemLogEventPublisher systemLogEventPublisher){
        this.systemLogEventPublisher = systemLogEventPublisher;
    }

}
