package com.example.httpclient.demohttpclient.cpntroller;

import com.example.httpclient.demohttpclient.entity.Person;
import com.example.httpclient.demohttpclient.entity.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.management.Agent;

import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

//用于测试sendcontroller发起请求的api接口
@RestController
public class GetController {

    /**
     * get不带参数
     * @return
     */
    @GetMapping(value = "/get")
    public String get(){

        return "接收到无参数的请求";
    }

    /**
     * get带参数的请求
     * @param name
     * @param age
     * @return
     */
    @GetMapping(value = "/getWithParam")
    public String getWithParam(@RequestParam(value = "myname") String name,
                               @RequestParam(value = "myage") String age){

        return "name: " + name + " age: " + age;
    }


    /**
     * 不带参数的post请求
     * @return
     */
    @PostMapping(value = "/postWithNoParam")
    public String postWithNoParam(){

        return "不带参数的post请求";
    }

    /**
     * 带普通参数的post请求
     * @param name
     * @param age
     * @return
     */
    @PostMapping(value = "/postWithParam")
    public Person postWithParam(String name, String age){

        Person p = new Person();
        p.setName(name);
        p.setAge(age);

        return p;
    }

    /**
     * 带对象参数和普通参数的post请求
     * @param user
     * @param name
     * @param age
     * @return
     */
    @PostMapping(value = "/postWithObjectAndParam")
    public Person postWithObjectAndParam(@RequestBody User user, String name, String age){

        System.out.println("get user = " + user + " name = " + name + " age = " + age);

        Person p = new Person();
        p.setAge(age);
        p.setName(name);
        p.setUser(user);

        return p;
    }

    /***
     * 接受文件  List<MultipartFile> multipartFiles
     * @param name
     * @param age
     * @param files
     * @return  接收到的文件的具体信息
     */
    @PostMapping(value = "/file")
    public String postFile(@RequestParam(value = "name") String name,
                           @RequestParam(value = "age") String age,
                           @RequestParam(value = "files") List<MultipartFile> files) throws UnsupportedEncodingException {

        StringBuffer sb = new StringBuffer();

        //防止中文乱码
        sb.append("\n");
        sb.append("name=").append(name).append("\tage=").append(age);

        String fileName;
        for (MultipartFile file : files){

            sb.append("\n文件信息:\n");
            fileName = file.getOriginalFilename();

            if (fileName == null){
                continue;
            }

            fileName = URLDecoder.decode(fileName, "utf-8");
            sb.append("\t文件名:").append(fileName);
            sb.append("\t文件大小:").append(file.getSize()*1.0/1024.0).append("kb");
            sb.append("\tContentType:").append(file.getContentType());
            sb.append("\n");

            try {

                //将文件复制到桌面上的files文件夹下面
                File toFile = new File("/Users/macofethan/Desktop/files/" + fileName);
                file.transferTo(toFile);
            }catch (Exception e){

                System.out.println("文件拷贝失败：" + e.getMessage());
            }
        }

        return sb.toString();
    }


    /***
     * 接受httpclient传过来的二进制流
     * @param name
     * @param is
     * @return
     */
    @PostMapping(value = "/is")
    public String getInputStream(@RequestParam(value = "name") String name,
                                 InputStream is) throws IOException {

        StringBuffer sb = new StringBuffer();

        sb.append("\nname=").append(name);
        sb.append("\n输入流的内容为:");

        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

        String line;
        float length = 0;

        while ((line = reader.readLine()) != null){

            length += line.length();
            sb.append(line);
        }

        System.out.println("接受到到的流长度: " + length/1024.0/1024.0 + "m");

        return sb.toString();
    }
}
