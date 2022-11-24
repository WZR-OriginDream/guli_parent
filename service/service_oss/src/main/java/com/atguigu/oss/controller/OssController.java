package com.atguigu.oss.controller;

import com.atguigu.commonutils.R;
import com.atguigu.oss.service.OssService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Origin Dream
 * @Date: 2022/11/5 13:31
 * @Decsription: JavaWeb Study
 * @Version: 1.0
 */
@RestController
@RequestMapping("/eduoss/fileoss")
@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    /**
     * 文件上传
     * @param file
     */
    @ApiOperation(value = "文件上传")
    @PostMapping
    public R upload(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) {
        String url = ossService.upload(file);
        //返回r对象
        return R.ok().message("文件上传成功").data("url", url);
    }
}
