package com.veeker.log.factory;

import com.veeker.log.enums.LogLevelType;
import com.veeker.log.services.ILogOutputService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

/**
 * 日志工厂
 *
 * @author ：qiaoliang
 */
public class LogConfigurationAdapter implements ApplicationContextAware,LogConfigurerComposite {

    private static Map<LogLevelType, ILogOutputService> LOG_MAP;

    @Override
    public ILogOutputService getLogConfiguration(LogLevelType type) {
        return LOG_MAP.get(type);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, ILogOutputService> beansOfType = applicationContext.getBeansOfType(
                ILogOutputService.class);
        LOG_MAP = new HashMap<>(beansOfType.size());
        beansOfType.forEach((k,v)->LOG_MAP.put(v.getFlag(),v));
    }

}
