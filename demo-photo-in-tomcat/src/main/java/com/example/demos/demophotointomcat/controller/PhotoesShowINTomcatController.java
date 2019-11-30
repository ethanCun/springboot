package com.example.demos.demophotointomcat.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Objects;
import java.util.Set;

@Controller
public class PhotoesShowINTomcatController {

    /**
     * 获取当前环境 测试 开发 生产
     * */
    @Value("${spring.profiles.active}")
    private String profiles;

    /**
     * IP
     * @return
     */
    @Value("${server.address}")
    private String ip;

    /**
     * port
     * @return
     */
    @Value("${server.port}")
    private String port;

    /**
     * context-path
     * @return
     */
    @Value("${server.servlet.context-path}")
    private String contextPath;

    @GetMapping(value = "/upload")
    public String upload(){

        return "/uploadImage";
    }


    @ResponseBody
    @PostMapping(value = "/file")
    public String uploafFile(@RequestParam("file") MultipartFile file, HttpServletRequest request){

        String basePath;

        //通过项目运行环境来设置图片存储位置
        if (Objects.equals(this.profiles,"test")){

            //测试环境
            basePath = System.getProperty("user.dir")+"/images/";
        }else {

            //开发和生产环境
            basePath = new File(request.getSession().getServletContext().getRealPath("/")).getParent() + "/images/";
        }

        File directory = new File(basePath);

        //创建文件路径
        if (!directory.exists()){

            directory.mkdirs();
        }

        File file1 = new File(basePath + file.getOriginalFilename());

        try {

            file.transferTo(file1);

        }catch (Exception e){

        }

        //返回图片地址供客户端显示 如果不设置context-path的话直接就可以返回
        if (Objects.equals(this.profiles, "test")){

            return this.ip+":"+this.port+this.contextPath+"/"+file.getOriginalFilename();
        }else {
            return this.ip+":"+this.port+"/images/"+file.getOriginalFilename();
        }
    }

    //在tomcat运行下 获取到的是tomcat的bin目录:/Library/apache-tomcat-8.5.15/bin
    //在本地运行下，获取到的是项目的根目录：/Users/macofethan/Desktop/springboot-demos/demo-photo-in-tomcat
    @ResponseBody
    @GetMapping(value = "/userDir")
    public String userDir(){

        return System.getProperty("user.dir");
    }

    //在tomcat运行下, 获取到的是项目的真实运行目录:/Library/apache-tomcat-8.5.15/webapps/photosInTomcat/
    //在本地运行下：/private/var/folders/tl/t_bq19kn6_g9y_3886ljxsgh0000gn/T/tomcat-docbase.1818733780640352669.8080/
    @ResponseBody
    @GetMapping(value = "/getContextPath")
    public String contextPath(HttpServletRequest request){

        return request.getSession().getServletContext().getRealPath("/");
    }

    //获取项目父类路径 如：webapps， webapps1(自建的目录)
    @ResponseBody
    @GetMapping(value = "/getwebapps")
    public String getwebappsPath(HttpServletRequest request){

        //项目路径文件
        File projFile = new File(request.getSession().getServletContext().getRealPath("/"));

        return projFile.getParent();
    }


    @ResponseBody
    @GetMapping(value = "/getRequestInfo")
    public void getRequestInfo(){

        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();

        System.out.println(request.getSession().getServletContext().getRealPath("/"));
    }

    @ResponseBody
    @GetMapping(value = "/getActiveProfile")
    public String getActiveProfile(){

        return this.profiles;
    }
}
