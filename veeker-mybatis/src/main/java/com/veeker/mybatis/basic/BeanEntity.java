package com.veeker.mybatis.basic;

import com.veeker.core.basics.Entity;

import java.io.Serializable;

/**
 * @author ：qiaoliang
 */
public class BeanEntity<T extends Serializable> implements Entity {

    private T id;

    public BeanEntity() {
    }

    public BeanEntity(T id) {
        this.id = id;
    }

    public void setId(T id) {
        this.id = id;
    }

    @Override
    public Serializable getId() {
        return id;
    }

}
