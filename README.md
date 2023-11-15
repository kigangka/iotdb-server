# 工程说明
本工程是一个基于 Spring Boot 2.5.6 版本搭建的 Spring Boot Demo工程，主要是实现IoTDB整合MyBatis实现CRUD操作
参考了网络上整合的办法和遇到的问题，相对资料较少，故此记录一下
## 当前基础依赖包

|                   依赖名称                    |   版本    |           用途           |
|:-----------------------------------------:|:-------:|:----------------------:|
|        spring-boot-starter-parent         |  2.5.6  |      spring 基础依赖包      |
|         mybatis-plus-boot-starter         | 3.5.3.1 |        orm操作框架         |
|                  lombok                   | 1.18.22 |         注解简化代码         |
|                hutool-all                 | 5.8.18  |        常用的工具类封装        |
|                 fastjson2                 | 2.0.38  |        json工具库         |
|                iotdb-jdbc                 |  1.2.1  |  apache IoTDB-JDBC工具库  |
|        forest-spring-boot-starter         | 1.5.33  | forest HTTP调用API框架     |

## 如何使用
在这之前需要安装和学习IoTDB相关知识，[IoTDB官网文档地址](https://www.timecho.com/docs/zh/UserGuide/V1.2.x/IoTDB-Introduction/What-is-IoTDB.html )   

当前工程Demo安装测试的是IoTDB **V1.12.1** 版本
>+ 使用gitclone下载[iotdb-server](https://gitee.com/kisang/iotdb-server.git)源码，Maven工程
>+ 创建数据库root.ln及时间序列等，可以参考[iotdb.sql](src%2Fmain%2Fresources%2Fdoc%2Fiotdb.sql) ，具体iotdb涉及的相关概念参考官网文档，这边可以简单理解为root.ln是数据库，weather为表，几个时间序列为表字段
>+ 修改配置信息，参考[application.yml](src%2Fmain%2Fresources%2Fapplication.yml)配置文件，修改url，username，password即可
>+ 启动工程，注意启动报配置文件错误的，可能是因为yml配置文件编码问题，在File/setting设置下编码，重新编译重启下。![code.jpg](src%2Fmain%2Fresources%2Fdoc%2Fimg%2Fcode.jpg)

## postman测试
服务启动成功后，可以使用postman进行测试，提供了三个接口：  
>+ 新增：http://localhost:9880/iot/weather/add  
>+ 分页查询： http://localhost:9880/iot/weather/page?page=1&pageSize=15
>+ 删除： http://localhost:9880/iot/weather/delete?startTime=2023-11-10 15:00:00&endTime=2023-11-10 15:02:46  

另外还有一个Task定时任务，每隔5分钟，调用公共API获取天气信息，自动插入数据库。

## 遇到的问题

1. 遇到的一些问题如启动时报错等，参考了[通过Mybatis实现iotdb数据库的连接遇到的问题以及解决方法](https://blog.csdn.net/baidu_31890799/article/details/130382310#comments_29690346)  


2. 插入数据时，字符串问题  
时间序列为字符串类型时，向数据库插入时，需要处理字符串前后加单引号，但是在测试过程中，如果直接格式化日期为String类型，却不需要前后加单引号  
![string.jpg](src%2Fmain%2Fresources%2Fdoc%2Fimg%2Fstring.jpg)


3. 查询数据时，顺序问题（大坑）  
起初使用select * 查询或者单独查询某几个时间序列时总是报错，查看日志发现是SQLException  
![sqlerror.jpg](src%2Fmain%2Fresources%2Fdoc%2Fimg%2Fsqlerror.jpg)  
但不明白错在哪里，后来下载了iotdb-jdbc源码调试发现，是实体（weatherEntity）时间序列顺序与返回数据自动映射出了问题，具体参考以下几张截图：  
![mapper-3.jpg](src%2Fmain%2Fresources%2Fdoc%2Fimg%2Fmapper-3.jpg)  
![entity-3.jpg](src%2Fmain%2Fresources%2Fdoc%2Fimg%2Fentity-3.jpg)  
![iotdbsrc-3.jpg](src%2Fmain%2Fresources%2Fdoc%2Fimg%2Fiotdbsrc-3.jpg)  
![iotdbsrc2-3.jpg](src%2Fmain%2Fresources%2Fdoc%2Fimg%2Fiotdbsrc2-3.jpg)  
![iotdbsrcerror-3.jpg](src%2Fmain%2Fresources%2Fdoc%2Fimg%2Fiotdbsrcerror-3.jpg)

基于以上问题，那就要求entity必须多加一个long类型的Time序列，查询为全查询，而且不能使用select * ，需要自行按entity顺序查询所有字段（Time默认返回可以省，但entity中不能省，且必须是第一个），具体此处不再截图说明，可以参考demo中entity和mapper文件。
不清楚是iotdb官网的SDK问题，还是说目前是不适配MyBatis，后续能适配当然最好了，总之目前简单的增，删，查功能还是可以实现的。  
![query.jpg](src%2Fmain%2Fresources%2Fdoc%2Fimg%2Fquery.jpg)

