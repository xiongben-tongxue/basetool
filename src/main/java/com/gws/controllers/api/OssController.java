package com.gws.controllers.api;

import com.gws.controllers.BaseController;
import com.gws.controllers.JsonResult;
import com.gws.dto.OperationResult;
import com.gws.services.oss.AliossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ucloud的处理文件的接口
 */
@RestController
@RequestMapping("/api/file/")
public class OssController extends BaseController {

    @Autowired
    private AliossService aliossService;

    /**
     * 单文件上传
     * @param file
     * @param bucket
     * @return
     */
    @RequestMapping("uploadFile")
    public JsonResult uploadFile(@RequestPart("file") MultipartFile file, String bucket){

        String fileUrl = aliossService.uploadFile(file, bucket);
        return success(fileUrl);
    }

    /**
     * 传输文件流
     * @param inputStream
     * @param bucket
     * @return
     */
    @RequestMapping("uploadFileStream")
    public JsonResult uploadFileStream(@RequestParam("inputStream") MultipartFile inputStream, String bucket){

        String fileUrl = aliossService.uploadFile(inputStream, bucket);
        return success(fileUrl);
    }

    /**
     * 传输多个文件流
     * @param inputStream1
     * @param bucket
     * @return
     */
    @RequestMapping("uploadTwoFileStream")
    public JsonResult uploadTwoFileStream(@RequestParam("inputStream1") MultipartFile inputStream1, @RequestParam("inputStream2") MultipartFile inputStream2,String bucket){
        MultipartFile[] arr = {inputStream1,inputStream2};
        List<String> fileUrls = aliossService.uploadFiles(arr, bucket);
        return success(fileUrls);
    }

    /**
     * 批量传输文件流
     * @param inputStreams
     * @param bucket
     * @return
     */
    @RequestMapping("uploadFilesStream")
    public JsonResult uploadFilesStream(@RequestParam("inputStreams") List<MultipartFile> inputStreams,String bucket){

        List<String> fileUrls = aliossService.uploadFilesStream(inputStreams, bucket);

        return success(fileUrls);
    }

    /**
     * 上传字符串
     * @param stringFile
     * @param bucket
     * @return
     */
    @RequestMapping("uploadStringFile")
    public JsonResult uploadStringFile(@RequestParam("stringFile") String stringFile, @RequestParam("bucket") String bucket){

        String fileUrl = aliossService.uploadStringFile(stringFile, bucket);
        Map<String,String> result = new HashMap<>();
        result.put("fileUrl",fileUrl);

        return success(result);
    }

    /**
     *
     * OSSObject实例包含文件所在的存储空间（Bucket）、文件的名称、Object Metadata以及一个输入流；
     * 现将流返回。
     * 通过操作输入流将文件的内容读取到文件或者内存中。而Object Metadata包含ETag、HTTP Header及自定义的元信息；
     * @param bucket
     * @param key
     * @return
     */
    @RequestMapping("downByStream")
    public JsonResult downByStream(String bucket,String key) throws IOException {

        InputStream result = aliossService.downByStream(bucket, key);

        return success(result);
    }

    /**
     * 文件的批量上传
     * @param files
     * @param bucket
     * @return
     */
    @RequestMapping("uploadFiles")
    public JsonResult uploadFiles(@RequestPart("files") MultipartFile[] files, String bucket){

        List<String> result = aliossService.uploadFiles(files,bucket);

        return success(result);
    }

    /**
     * 上传文件到公共服务上去
     * @param inputStream
     * @param fileName
     * @return
     */
    @RequestMapping("uploadFileToBasetool")
    public JsonResult uploadFileToBasetool(InputStream inputStream,String fileName) {
        OperationResult<Boolean> result= aliossService.uploadFileToBasetool(inputStream, fileName);
        return success(result.getEntity());
    }

    /**
     * 通过地址下载文件并保存在本地
     * @param fileUrl
     * @param saveAsPath
     * @return
     */
    @RequestMapping("downloadFile")
    public JsonResult downloadFile(String fileUrl, String saveAsPath){
        boolean result = aliossService.download(fileUrl,saveAsPath);
        return success(result);
    }
}
