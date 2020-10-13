package com.veeker.mybatis.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.veeker.core.services.IOperatorService;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;
import java.util.Objects;

/**
 * mybatis默认配置
 *
 * @author ：qiaoliang
 */
public class ModelMetaObjectHandler implements MetaObjectHandler {

    private final IOperatorService operatorService;
    /**创建者**/
    private static final String CREATE_BY = "createBy";
    /**创建时间**/
    private static final String CREATE_TIME = "createTime";
    /**修改者**/
    private static final String UPDATE_BY = "updateBy";
    /**修改时间**/
    private static final String UPDATE_TIME = "updateTime";

    public ModelMetaObjectHandler(IOperatorService operatorService) {
        this.operatorService = operatorService;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        // 获取到需要被填充的字段值
        if(Objects.isNull(getFieldValByName(CREATE_TIME, metaObject))){
            setFieldValByName(CREATE_TIME, new Date(),metaObject);
        }
        if(Objects.isNull(getFieldValByName(CREATE_BY, metaObject))){
            setFieldValByName(CREATE_BY, operatorService.operator(),metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 获取到需要被填充的字段值
        if(Objects.isNull(getFieldValByName(UPDATE_TIME, metaObject))){
            setFieldValByName(UPDATE_TIME, new Date(),metaObject);
        }
        if(Objects.isNull(getFieldValByName(UPDATE_BY, metaObject))){
            setFieldValByName(UPDATE_BY, operatorService.operator(),metaObject);
        }
    }


}
