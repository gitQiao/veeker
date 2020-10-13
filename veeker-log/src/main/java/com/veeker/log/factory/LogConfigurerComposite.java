package com.veeker.log.factory;


import com.veeker.log.enums.LogLevelType;
import com.veeker.log.services.ILogOutputService;

/**
 * 工厂接口
 *
 * @author ：qiaoliang
 */
public interface LogConfigurerComposite {

    ILogOutputService getLogConfiguration(LogLevelType type);

}
