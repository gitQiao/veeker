package com.veeker.mybatis.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.veeker.mybatis.basic.Entity;
import com.veeker.mybatis.service.BaseService;

/**
 * @author ：qiaoliang
 * @date ：2020-07-21
 */
public abstract class BaseServiceImpl <Mapper extends BaseMapper<E>,E extends Entity>
        extends ServiceImpl<Mapper,E>
        implements BaseService<E> {
}
