package com.atguigu.eduservice.entity.chapter;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Origin Dream
 * @Date: 2022/11/13 22:17
 * @Decsription: JavaWeb Study
 * @Version: 1.0
 */
@ApiModel(value = "课时信息")
@Data
public class VideoVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private Boolean free;

    private String videoSourceId;//视频id
}