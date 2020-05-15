package com.springboot.module.common;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface IMapper<T> extends MySqlMapper<T>, Mapper<T> {
}
