package com.example.demos.demophotointomcat.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Objects;

@Configuration
public class resoureceConfig extends WebMvcConfigurerAdapter {

    @Value("${spring.profiles.active}")
    private String profiles;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        if (Objects.equals(this.profiles,"test")){

            //测试环境:图片存在当前项目images文件夹下
            registry.addResourceHandler("/**")
                    .addResourceLocations("file:"+System.getProperty("user.dir")+"/images/");
        }else {

            //开发和生产环境部署在当前webapps下的images文件夹下面
            registry.addResourceHandler("/**")
                    .addResourceLocations("file:"+"/images/");
        }

        super.addResourceHandlers(registry);
    }
}
