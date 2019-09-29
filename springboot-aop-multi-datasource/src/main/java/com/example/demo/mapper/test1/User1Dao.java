package com.example.demo.mapper.test1;

import com.example.demo.entity.test1.User1;
import org.apache.ibatis.annotations.Insert;

public interface User1Dao {

    int saveToDb1(User1 user1);
}
