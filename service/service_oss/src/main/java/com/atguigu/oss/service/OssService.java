package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: Origin Dream
 * @Date: 2022/11/5 13:32
 * @Decsription: JavaWeb Study
 * @Version: 1.0
 */
public interface OssService {
    String upload(MultipartFile file);
}
