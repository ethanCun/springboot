package com.example.demo.mapper.test2;

import com.example.demo.entity.test2.User2;
import org.apache.ibatis.annotations.Insert;

public interface User2Dao {

    int saveToDb2(User2 user2);
}
