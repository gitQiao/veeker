package com.veeker.mybatis.config;

import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.veeker.core.services.IOperatorService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ：qiaoliang
 */
@Configuration
@EnableTransactionManagement
public class MyBatisPlusConfiguration {

    /**
     * 分页插件
     *
     * @author ：qiaoliang
     * @return com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
     */
    @Bean
    @ConditionalOnMissingBean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor().setOverflow(true);
    }

    /**
     * 默认配置
     *
     * @author ：qiaoliang
     * @return com.avocado.boot.starter.mybatis.config.ModelMetaObjectHandler
     */
    @Bean
    @ConditionalOnMissingBean
    public ModelMetaObjectHandler modelMetaObjectHandler(IOperatorService operatorService){
        return new ModelMetaObjectHandler(operatorService);
    }

    @Bean
    @ConditionalOnMissingBean
    public ISqlInjector sqlInjector() {
        return new DefaultSqlInjector();
    }

}
