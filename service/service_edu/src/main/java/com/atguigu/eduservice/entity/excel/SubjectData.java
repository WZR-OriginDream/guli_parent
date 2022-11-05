package com.atguigu.eduservice.entity.excel;

/**
 * @Author: Origin Dream
 * @Date: 2022/11/5 21:38
 * @Decsription: JavaWeb Study
 * @Version: 1.0
 */
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
@Data
public class SubjectData {
    @ExcelProperty(index = 0)
    private String oneSubjectName;
    @ExcelProperty(index = 1)
    private String twoSubjectName;
}