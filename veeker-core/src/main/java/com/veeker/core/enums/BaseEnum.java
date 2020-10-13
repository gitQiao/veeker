package com.veeker.core.enums;

/**
 *  数字类型枚举接口
 *
 * @author ：qiaoliang
 * @date 2020-08-26 11:34
 */
public interface BaseEnum<E extends Enum<?>, T> {

    /**
     * 获取值
     */
    T getValue();

    /**
     * 描述
     */
    default String getDesc(){
        return null;
    }

}
