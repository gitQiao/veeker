package com.veeker.mybatis.basic;

import java.io.Serializable;

/**
 * @author ：qiaoliang
 */
public class Entity<T> implements Serializable{

    private T id;

    public Entity() {
    }

    public Entity(T id) {
        this.id = id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }

}
