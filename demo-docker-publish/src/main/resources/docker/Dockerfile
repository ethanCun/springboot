# 使用docker部署springboot项目参考链接：https://blog.csdn.net/dhaiuda/article/details/82228186

# FROM：表示基础镜像，即运行环境
FROM java:8

# VOLUME：一个特别指定的目录，用于存储数据，该命令的作用是在/var/lib/docker创建一个名为tmp的目录，
# 在开启redis服务时，需要特别指定redis的数据存储在哪个文件夹，此时这个命令就十分有用
VOLUME /tmp

# ADD：拷贝文件并且重命名
ADD dockerPublish.jar app.jar

# EXPOSE：并不是真正的发布端口，这个只是容器部署人员与建立image的人员之间的交流，
# 即建立image的人员告诉容器布署人员容器应该映射哪个端口给外界
EXPOSE 8080

# ENTRYPOINT：容器启动时运行的命令，相当于我们在命令行中输入java -jar xxxx.jar，
# 为了缩短 Tomcat 的启动时间，添加java.security.egd的系统属性指向/dev/urandom作为 ENTRYPOINT
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]