package com.example.demo.app.entity;

import com.sun.xml.internal.ws.developer.Serialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Serialization
public class User {

    private Integer id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    private String token;


}
