package com.example.demos.demoforappfront.controller;

import com.example.demos.demoforappfront.entity.User;
import com.example.demos.demoforappfront.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/queryUserList")
    public List<User> queryUserList(){

        return this.userService.queryUserList();
    }

    //获取名称类似的所有用户
    @GetMapping(value = "/queryUserListLike")
    public List<User> queryUserListLike(String name){

        return this.userService.queryUserListLike(name);
    }

    //获取app传过来的客户信息 类似于登录
    @PostMapping(value = "/queryUser")
    public User queryUser(@RequestBody User user, HttpServletRequest request){

        Enumeration<String> header = request.getHeaderNames();

        while (header.hasMoreElements()){

            String headerName = header.nextElement();

            System.out.println("header = " + headerName
            + ":" + request.getHeader(headerName));
        }

        return user;
    }


    @PostMapping(value = "/file")
    public HashMap<String, Object> queryFile(@RequestParam("files") List<MultipartFile> files) throws IOException {


        for (int i = 0; i < files.size(); i++) {

            MultipartFile multipartFile = files.get(i);

            System.out.println("file = " + multipartFile.getName());
            System.out.println("origianlName = " + multipartFile.getOriginalFilename());
            System.out.println("Content-Type = " + multipartFile.getContentType());

            try {

                String fileName = System.currentTimeMillis() + "-" + (i+1) + ".png";

                //System.getProperty("user.dir")会定位到项目的根目录，可以得到该工程项目所有文件的相关路径及环境配置信息
                //System.getProperty("user.dir")
                multipartFile.transferTo(new File(System.getProperty("user.dir")
                        + "/src/main/resources/images/" + fileName));

            }catch (Exception e){

            }
        }


        HashMap<String, Object> map = new HashMap<>();
        map.put("file", files.get(0).getName());
        map.put("size", files.get(0).getSize());

        return map;
    }

    @PostMapping(value = "/uploadVedio")
    public HashMap<String, Object> uploadVedio(@RequestParam("vedio") MultipartFile file,
                                               User user) throws IOException {

        MultipartFile multipartFile = file;

        System.out.println("file = " + multipartFile.getName());
        System.out.println("origianlName = " + multipartFile.getOriginalFilename());
        System.out.println("Content-Type = " + multipartFile.getContentType());

        try {

            String fileName = System.currentTimeMillis() + "-" +multipartFile.getOriginalFilename();

            //System.getProperty("user.dir")会定位到项目的根目录，可以得到该工程项目所有文件的相关路径及环境配置信息
            //System.getProperty("user.dir")
            multipartFile.transferTo(new File(System.getProperty("user.dir")
                    + "/src/main/resources/vedios/" + fileName));

        }catch (Exception e){

        }


        HashMap<String, Object> map = new HashMap<>();
        map.put("file", file.getName());
        map.put("size", file.getSize());
        map.put("user", user);

        return map;
    }
}
