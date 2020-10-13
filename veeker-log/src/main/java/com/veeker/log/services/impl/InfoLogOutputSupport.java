package com.veeker.log.services.impl;

import com.veeker.log.domain.Log;
import com.veeker.log.enums.LogLevelType;
import com.veeker.log.services.ILogOutputService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author ：qiaoliang
 */
@Component
public class InfoLogOutputSupport extends AbstractLogOutputSupport implements ILogOutputService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InfoLogOutputSupport.class);

    @Override
    public void businessLog(Log log) {
        LOGGER.info("发生业务异常时间：{}", log.getErrorTime());
        LOGGER.info("抛出业务异常：{}", log.getErrorCountent());
    }

    @Override
    public void before(Log log) {
        LOGGER.info("请求Url : {}", log.getUrl());
        LOGGER.info("请求方式 : {}", log.getMethod());
        LOGGER.info("请求方法 : {}", log.getMethodName());
        LOGGER.info("功能模块 : {}", log.getBusinessType());
        LOGGER.info("方法描述 : {}", log.getDiscription());
        LOGGER.info("请求用户 : {}", log.getCurrUserId());
        LOGGER.info("用户类别 : {}", log.getOperatorType());
        LOGGER.info("请求参数 : {}", log.getInputParam());
        LOGGER.info("请求开始时间：{}", log.getStartTime());
    }

    @Override
    public void doAfterReturning(Log log) {
        LOGGER.info("请求结束时间：{}", log.getEndTime());
        LOGGER.info("请求耗时：{}", log.getConsumingTime());
        // 处理完请求，返回内容
        LOGGER.info("请求返回 : {}", log.getOutParam());
    }

    @Override
    public LogLevelType getFlag() {
        return LogLevelType.INFO;
    }

}
