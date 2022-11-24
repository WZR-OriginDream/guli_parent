package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author: Origin Dream
 * @Date: 2022/11/16 19:01
 * @Decsription: JavaWeb Study
 * @Version: 1.0
 */
@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {

    @DeleteMapping("/eduvod/video/removeVideo/{id}")
    public R removeVideo(@PathVariable("id") String id);

    @DeleteMapping("/eduvod/video/delete-batch")
    public R removeVideoList(@RequestParam("videoIdList") List<String> videoIdList);
}
