package com.atguigu.vod.servicel.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.atguigu.vod.servicel.VodService;
import com.atguigu.vod.utils.ConstantPropertiesUtil;
import com.atguigu.vod.utils.InitObject;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author: Origin Dream
 * @Date: 2022/11/15 23:22
 * @Decsription: JavaWeb Study
 * @Version: 1.0
 */
@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadVideo(MultipartFile file) {
        try {
            String fileName= file.getOriginalFilename();
            String title = fileName.substring(0, fileName.lastIndexOf("."));
            InputStream inputStream= file.getInputStream();
            UploadStreamRequest request = new UploadStreamRequest(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET, title, fileName, inputStream);
            /* 点播服务接入点 */
            request.setApiRegionId("cn-shenzhen");
            /* ECS部署区域*/
            // request.setEcsRegionId("cn-shenzhen");
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);

            String videoId=null;
            if (response.isSuccess()) {
                videoId= response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId= response.getVideoId();
            }
            return videoId;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void removeVideo(String id) {
        try{
            DefaultAcsClient client = InitObject.initVodClient(
                    ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(id);
            DeleteVideoResponse response = client.getAcsResponse(request);
        }catch (ClientException e){
            throw new GuliException(20001, "视频删除失败");
        }
    }

    @Override
    public void removeVideoList(List videoIdList) {
        try {
//初始化
            DefaultAcsClient client = InitObject.initVodClient(
                    ConstantPropertiesUtil.ACCESS_KEY_ID,
                    ConstantPropertiesUtil.ACCESS_KEY_SECRET);
//创建请求对象
//一次只能批量删20个
            String str = org.apache.commons.lang.StringUtils.join(videoIdList.toArray(), ",");
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(str);
//获取响应
            DeleteVideoResponse response = client.getAcsResponse(request);

        } catch (ClientException e) {
            throw new GuliException(20001, "视频删除失败");
        }
    }
}
