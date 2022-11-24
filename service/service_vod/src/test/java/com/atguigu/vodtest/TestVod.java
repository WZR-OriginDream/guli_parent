package com.atguigu.vodtest;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadVideoRequest;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import sun.tools.jar.resources.jar;

import java.util.List;

/**
 * @Author: Origin Dream
 * @Date: 2022/11/15 15:03
 * @Decsription: JavaWeb Study
 * @Version: 1.0
 */
public class TestVod {
    public static void main(String[] args) throws Exception {
        getPlayInfo();
//        getPlayAuth();
//        testUploadVideo1("LTAI5tPJxSUb6tWRtZxFiedN","ex3sDQSsVTJph5MwmST5rMD9v6FURT","测试视频","E:/test.mp4");
//        testUploadVideo2("LTAI5tPJxSUb6tWRtZxFiedN","ex3sDQSsVTJph5MwmST5rMD9v6FURT","测试视频","E:/test.mp4");
    }

    /**
     * 本地文件上传接口
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @param title
     * @param fileName
     */
    private static void testUploadVideo1(String accessKeyId, String accessKeySecret, String title, String fileName) {
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);

//        request.setStorageLocation("outin-a4f1c39b648c11ed836700163e021072.oss-cn-shenzhen.aliyuncs.com");
        request.setApiRegionId("cn-shenzhen");

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);

        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }

    private static void testUploadVideo2(String accessKeyId, String accessKeySecret, String title, String fileName) {
        UploadVideoRequest request = new UploadVideoRequest(accessKeyId, accessKeySecret, title, fileName);
        /* 可指定分片上传时每个分片的大小，默认为2M字节 */
        request.setPartSize(2 * 1024 * 1024L);
        /* 可指定分片上传时的并发线程数，默认为1，(注：该配置会占用服务器CPU资源，需根据服务器情况指定）*/
        request.setTaskNum(1);

//        request.setStorageLocation("outin-a4f1c39b648c11ed836700163e021072.oss-cn-shenzhen.aliyuncs.com");
        request.setApiRegionId("cn-shenzhen");

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadVideoResponse response = uploader.uploadVideo(request);

        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else {
            /* 如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因 */
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
    }



    public static void getPlayAuth() throws Exception {
        DefaultAcsClient client = InitObject.initVodClient("LTAI5tPJxSUb6tWRtZxFiedN", "ex3sDQSsVTJph5MwmST5rMD9v6FURT");
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        GetVideoPlayAuthResponse response = new GetVideoPlayAuthResponse();
        try {
            request.setVideoId("6b7731bd0c484b8d9e0d138f4b592ce8");
            response = client.getAcsResponse(request);
            //播放凭证
            System.out.print("PlayAuth = " + response.getPlayAuth() + "\n");
            //VideoMeta信息
            System.out.print("VideoMeta.Title = " + response.getVideoMeta().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }

    public static void getPlayInfo() throws Exception {
        //初始化客户端、请求对象和相应对象
        DefaultAcsClient client = InitObject.initVodClient("LTAI5tPJxSUb6tWRtZxFiedN", "ex3sDQSsVTJph5MwmST5rMD9v6FURT");
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        GetPlayInfoResponse response = new GetPlayInfoResponse();
        try {
            //设置请求参数
            //注意：这里只能获取非加密视频的播放地址
//            request.setEndpoint("vod.cn-shenzhen.aliyuncs.com");
            request.setVideoId("6b7731bd0c484b8d9e0d138f4b592ce8");

            //获取请求响应
            response = client.getAcsResponse(request);
            //输出请求结果

            List<GetPlayInfoResponse.PlayInfo> playInfoList =
                    response.getPlayInfoList();
            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
            }
            //Base信息
            System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");
        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }

}
