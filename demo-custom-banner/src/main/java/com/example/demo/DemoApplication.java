package com.example.demo;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        //控制banner
//        SpringApplication application = new SpringApplication(DemoApplication.class);
//
//        /**
//         * OFF 关闭
//         * CLOSED 后台控制台输出，默认就是这种
//         * LOG 日志输出
//         */
//        application.setBannerMode(Banner.Mode.LOG);
//
//        application.run(args);

        /*
        * 我们可以在resource目录下面放入一个banner.txt文件，Spring Boot启动项目的时候就会优先启动这个文件中的内容。
         * 两个生成banner的网址:
        *
        * http://www.network-science.de/ascii/
        * http://patorjk.com/software/taag/
        * **/

        SpringApplication.run(DemoApplication.class, args);
    }

}
