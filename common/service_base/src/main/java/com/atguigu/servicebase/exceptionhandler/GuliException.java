package com.atguigu.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Origin Dream
 * @Date: 2022/11/3 10:55
 * @Decsription: JavaWeb Study
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException{
    private Integer code;
    private String msg;
}
