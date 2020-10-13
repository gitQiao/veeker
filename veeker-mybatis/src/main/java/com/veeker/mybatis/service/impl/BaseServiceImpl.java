package com.veeker.mybatis.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.veeker.mybatis.basic.BeanEntity;
import com.veeker.mybatis.service.BaseService;

/**
 * @author ：qiaoliang
 * @date ：2020-07-21
 */
public abstract class BaseServiceImpl <Mapper extends BaseMapper<E>,E extends BeanEntity>
        extends ServiceImpl<Mapper,E>
        implements BaseService<E> {
}
