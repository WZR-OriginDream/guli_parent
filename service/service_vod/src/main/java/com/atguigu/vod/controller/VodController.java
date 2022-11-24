package com.atguigu.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.atguigu.commonutils.R;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.atguigu.vod.servicel.VodService;
import com.atguigu.vod.utils.ConstantPropertiesUtil;
import com.atguigu.vod.utils.InitObject;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author: Origin Dream
 * @Date: 2022/11/15 23:20
 * @Decsription: JavaWeb Study
 * @Version: 1.0
 */
@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    @PostMapping("uploadVideo")
    public R uploadVideo(
            @ApiParam(name = "file", value = "文件", required = true)
            @RequestParam("file") MultipartFile file) throws Exception {
        String videoId = vodService.uploadVideo(file);
        return R.ok().message("视频上传成功").data("videoId", videoId);
    }

    @DeleteMapping("removeVideo/{id}")
    public R removeVideo(
            @ApiParam(name = "id", value = "云端视频id", required = true)
            @PathVariable String id) {
        vodService.removeVideo(id);
        return R.ok().message("视频删除成功");
    }


    /**
     * 批量删除视频
     * @param videoIdList
     * @return
     */
    @DeleteMapping("delete-batch")
    public R removeVideoList(
            @ApiParam(name = "videoIdList", value = "云端视频id", required = true)
            @RequestParam("videoIdList") List<String> videoIdList){
        vodService.removeVideoList(videoIdList);
        return R.ok().message("视频删除成功");
    }

    //根据视频id获取视频凭证
    @GetMapping("getPlayAuth/{id}")
    public R getPlayAuth(@PathVariable String id) {
        try {
            //创建初始化对象
            DefaultAcsClient client =
                    InitObject.initVodClient(ConstantPropertiesUtil.ACCESS_KEY_ID, ConstantPropertiesUtil.ACCESS_KEY_SECRET);
            //创建获取凭证request和response对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            //向request设置视频id
            request.setVideoId(id);
            //调用方法得到凭证
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return R.ok().data("playAuth",playAuth);
        }catch(Exception e) {
            throw new GuliException(20001,"获取凭证失败");
        }
    }
}
