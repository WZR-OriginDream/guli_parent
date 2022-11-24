package com.atguigu.vod.servicel;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: Origin Dream
 * @Date: 2022/11/15 23:22
 * @Decsription: JavaWeb Study
 * @Version: 1.0
 */
public interface VodService {
    String uploadVideo(MultipartFile file);

    void removeVideo(String id);

    void removeVideoList(List videoIdList);
}
