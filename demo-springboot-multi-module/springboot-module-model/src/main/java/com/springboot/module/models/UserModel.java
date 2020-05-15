package com.springboot.module.models;

import com.springboot.module.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserModel {

    private User user;

    public UserModel(User user){
        this.user = user;
    }
}
