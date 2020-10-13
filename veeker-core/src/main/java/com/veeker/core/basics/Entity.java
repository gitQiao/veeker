package com.veeker.core.basics;

import java.io.Serializable;

/**
 * 领域实体接口。所有实体类都要直接或间接实现这个接口。它主要起标记作用，以便于统一处理系统中的实体等。
 *
 * @author ：qiaoliang
 */
public interface Entity extends Serializable {

    /** 取得实体的Id。实体类的每个实例都必须有个唯一Id以标识自身。**/
    Serializable getId();

}
