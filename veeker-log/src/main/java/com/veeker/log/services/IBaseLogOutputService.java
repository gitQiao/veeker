package com.veeker.log.services;

import com.veeker.log.domain.Log;

/**
 * @author ：qiaoliang
 */
public interface IBaseLogOutputService {

    void before(Log log);

    void doAfterReturning(Log log);

    void doAfterThrowing(Log log);
}
