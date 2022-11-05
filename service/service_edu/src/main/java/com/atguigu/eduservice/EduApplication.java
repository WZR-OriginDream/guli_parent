package com.atguigu.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: Origin Dream
 * @Date: 2022/11/2 23:12
 * @Decsription: JavaWeb Study
 * @Version: 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.atguigu"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
