package com.veeker.log.services.impl;

import com.veeker.log.domain.Log;
import com.veeker.log.enums.LogLevelType;
import com.veeker.log.services.ILogOutputService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：qiaoliang
 */
public abstract class AbstractLogOutputSupport implements ILogOutputService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractLogOutputSupport.class);

    @Override
    public abstract void businessLog(Log log);

    @Override
    public abstract void before(Log log);

    @Override
    public void doAfterReturning(Log log){

    }

    @Override
    public void doAfterThrowing(Log log) {
        LOGGER.error("发生异常时间：{}", log.getErrorTime());
        LOGGER.error("抛出异常：{}", log.getErrorCountent());
    }

    @Override
    public abstract LogLevelType getFlag();

}
