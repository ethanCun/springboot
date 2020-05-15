package com.springboot.module.mapper;

import com.springboot.module.common.IMapper;
import com.springboot.module.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends IMapper<User> {
}