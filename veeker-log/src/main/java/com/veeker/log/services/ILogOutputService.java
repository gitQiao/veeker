package com.veeker.log.services;


import com.veeker.log.domain.Log;
import com.veeker.log.enums.LogLevelType;

/**
 * @author ：qiaoliang
 */
public interface ILogOutputService extends IBaseLogOutputService {

    /**
     * 自定义异常输出
     *
     * @author ：qiaoliang
     * @param log : 日志信息
     */
    void businessLog(Log log);

    /**
     * 实现方式的唯一标识
     *
     * @author ：qiaoliang
     * @return com.sandwind.cloud.common.core.enums.LogLevelType
     */
    LogLevelType getFlag();

}
