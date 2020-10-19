package com.veeker.core.basics;

import java.io.Serializable;

/**
 * 领域聚合根接口。所有聚合根类都要直接或间接实现这个接口。它主要起标记作用，以便于统一处理系统中的聚合根等。
 *
 * @author ：qiaoliang
 */
public interface AggregateRoot extends Serializable {
}
