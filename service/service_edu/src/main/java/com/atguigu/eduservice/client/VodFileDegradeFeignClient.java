package com.atguigu.eduservice.client;

import com.atguigu.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Origin Dream
 * @Date: 2022/11/16 21:03
 * @Decsription: JavaWeb Study
 * @Version: 1.0
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public R removeVideo(String videoId) {
        return R.error().message("time out");
    }
    @Override
    public R removeVideoList(List videoIdList) {
        return R.error().message("time out");
    }
}