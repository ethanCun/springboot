package com.example.demo.dao;

import com.example.demo.entity.Human;
import org.apache.ibatis.annotations.*;

import java.util.List;


//测试注解之前在application.yml中关闭mybatis.mapper-locations
public interface HumanDao {

//    @Select("select * from `t_human`")
//    @Results({
//            @Result(property = "id", column = "id"),
//            @Result(property = "name", column = "name"),
//            @Result(property = "age", column = "age")
//    })
    List<Human> allHumans();

//    @Select("select * from `t_human` where id=#{id}")
//    @Results(value = {
//            @Result(property = "id", column = "id"),
//            @Result(property = "name", column = "name"),
//            @Result(property = "age", column = "age")
//    })
    Human getHumanById(int id);

//    @Insert("insert into `t_human` (id, name, age) values (#{id}, #{name}, #{age})")
    int insertHuman(Human human);

//    @Update("update `t_human` set name=#{name}, age=#{age} where id=#{id}")
    int updateHuman(Human human);

//    @Delete("delete from `t_human` where id=#{id}")
    int deleteHumanById(int id);
}
