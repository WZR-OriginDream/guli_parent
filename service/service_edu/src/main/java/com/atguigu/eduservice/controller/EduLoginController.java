package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Origin Dream
 * @Date: 2022/11/4 11:08
 * @Decsription: JavaWeb Study
 * @Version: 1.0
 */
@Api(tags = "登录")
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {

    @PostMapping("login")
    public R login(){
        return R.ok().data("token","admin");
    }

    @GetMapping("info")
    public R info(){
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://guli-file-190513.oss-cn-beijing.aliyuncs.com/avatar/default.jpg");
    }
}
