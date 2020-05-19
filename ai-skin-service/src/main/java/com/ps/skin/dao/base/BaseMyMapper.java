package com.ps.skin.dao.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface BaseMyMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
