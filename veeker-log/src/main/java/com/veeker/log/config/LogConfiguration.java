package com.veeker.log.config;

import com.veeker.log.aop.LogAspect;
import com.veeker.log.event.LogConfigurerSupport;
import com.veeker.log.event.SystemLogEventPublisher;
import com.veeker.log.factory.LogConfigurationAdapter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * log 初始化配置
 *
 * @author ：qiaoliang
 */
@ComponentScan(basePackages = {
        "com.veeker.log.services"
})
@Import(LogAspect.class)
@Configuration
public class LogConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public LogConfigurerSupport toLogConfigurerSupport(SystemLogEventPublisher systemLogEventPublisher){
        LogConfigurerSupport logConfigurerSupport = new LogConfigurerSupport();
        logConfigurerSupport.systemLogEventPublisher(systemLogEventPublisher);
        return logConfigurerSupport;
    }

    @Bean
    @ConditionalOnMissingBean
    public SystemLogEventPublisher toSystemLogEventPublisher(){
        return new SystemLogEventPublisher();
    }

    @Bean
    public LogConfigurationAdapter toLogConfigurationAdapter(){
        return new LogConfigurationAdapter();
    }

}
