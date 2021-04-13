package com.lizhiqiang.simple;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
    DruidDataSourceAutoConfigure查看源码:
    1.表明druid是根据spring.datasource.druid找jdbc属性的，如果not found,则根据spring.datasource找jdbc属性，
    一般而言这是不会出现错误的。但是我这里使用了shardingjdbc
    2.就很显然了，他根据spring.datasource.druid或者spring.datasource确实找不到，因为我的结构是spring.shardingsphere.datasource
        解决方式1：
            如果我们用的jar包是druid-spring-boot-starter，则在启动类上排除druid自动配置
            @SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
        解决方式2：不用druid-spring-boot-starter,改为druid
 */
@MapperScan("com.lizhiqiang.simple.dao")//映射mapper地址
@SpringBootApplication(exclude = {DruidDataSourceAutoConfigure.class})
public class ShardingJdbcSimpleApplication {

    public static void main(String[] args){
        SpringApplication.run(ShardingJdbcSimpleApplication.class, args);
    }
}
