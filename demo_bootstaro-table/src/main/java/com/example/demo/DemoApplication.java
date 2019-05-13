package com.example.demo;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@SpringBootApplication
@MapperScan(basePackages = "com.example.demo.dao")
public class DemoApplication extends WebMvcConfigurationSupport {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    //这里配置静态资源文件的路径导包都是默认的直接导入就可以
    //配置spring启动的时候加载static下面的静态文件一起运行
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {

        //表示在磁盘static目录下的所有资源会被解析为以下的路径 classpath:/static/
        //http://localhost:8080/static/img/test.png
        registry.addResourceHandler("/static/**")
                .addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");

        super.addResourceHandlers(registry);
    }

//    @Bean
//    public Connector httpConnector() {
//
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setScheme("http");
//
//        //Connector监听的http的端口号
//        connector.setPort(8080);
//        connector.setSecure(false);
//
//        //监听到http的端口号后转向到的https的端口号
//        connector.setRedirectPort(8443);
//        return connector;
//    }
//
//    @Bean
//    public TomcatServletWebServerFactory tomcatServletWebServerFactory(Connector connector){
//
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory(){
//
//            @Override
//            protected void postProcessContext(Context context) {
//
//                SecurityConstraint securityConstraint=new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection=new SecurityCollection();
//                collection.addPattern("/*");
//                securityConstraint.addCollection(collection);
//                context.addConstraint(securityConstraint);
//            }
//        };
//
//        tomcat.addAdditionalTomcatConnectors(connector);
//
//        return tomcat;
//    }
}
