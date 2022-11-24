package com.atguigu.eduservice.entity.chapter;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Origin Dream
 * @Date: 2022/11/13 22:17
 * @Decsription: JavaWeb Study
 * @Version: 1.0
 */
@ApiModel(value = "章节信息")
@Data
public class ChapterVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private List<VideoVo> children = new ArrayList<>();
}