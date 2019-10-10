package com.czy.demos.rabbitmqdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * rabbitmq命令执行报错command not found参考链接：
 * https://blog.csdn.net/topdeveloperr/article/details/99549098
 *
 * macos下安装rabbitmq参考链接：
 * https://www.jianshu.com/p/860a9a675fe6
 *
 * rabbitmq用户管理（添加用户、修改密码、删除用户、查看用户列表）； 角色管理， 权限管理参考链接:
 * https://www.cnblogs.com/xinxiucan/p/7940953.html
 * https://www.cnblogs.com/AloneSword/p/4200051.html
 *
 * springboot集成rabbitmsq参考链接：
 * https://www.cnblogs.com/skychenjiajun/p/9037324.html
 *
 * springboot集成rabbitmq报错： Failed to check/redeclare auto-delete queue(s)
 * https://blog.csdn.net/qq_22638399/article/details/81705606
 *
 * rabbitmq操作命令：
 * 启动：rabbitmq-server start
 * 查看是否启动成功：rabbitmq-server status
 * 关闭：rabbitmqctl stop
 *
 * */
@SpringBootApplication
public class RabbitmqDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitmqDemoApplication.class, args);
    }

}
