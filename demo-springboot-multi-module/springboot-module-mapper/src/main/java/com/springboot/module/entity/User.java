package com.springboot.module.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Table(name = "sys_user")
public class User {

    @Id
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 是否有效  1有效  2无效
     */
    @Column(name = "delete_status")
    private String deleteStatus;

}