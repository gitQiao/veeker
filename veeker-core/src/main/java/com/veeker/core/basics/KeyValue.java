package com.veeker.core.basics;

import cn.hutool.core.builder.EqualsBuilder;
import cn.hutool.core.builder.HashCodeBuilder;
import cn.hutool.core.lang.Assert;

import java.io.Serializable;

/**
 * 键值对数据类型
 *
 * @author ：qiaoliang
 */
public class KeyValue<K,V> implements Serializable {

    private K key;

    private V value;

    public KeyValue(K key, V value) {
        Assert.notNull(key, "Key must not be null!");
        this.key = key;
        this.value = value;
    }

    public static <K,V> KeyValue<K,V> create(K key,V value){
        return new KeyValue<>(key,value);
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }



    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(key).append(value).toHashCode();
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KeyValue)) {
            return false;
        }
        KeyValue<K, V> that = (KeyValue<K, V>) other;
        return new EqualsBuilder().append(this.key, that.key)
                .append(this.value, that.value).isEquals();
    }

    @Override
    public String toString() {
        return "KeyValue [key=" + key + ", value=" + value + "]";
    }

}
