package com.atguigu.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Origin Dream
 * @Date: 2022/11/6 0:42
 * @Decsription: JavaWeb Study
 * @Version: 1.0
 */
@Data
public class OneSubject {
    private String id;
    private String title;
    private List<TwoSubject> children = new ArrayList<>();
}
