package com.gws.services.oss.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.gws.GwsWebApplication;
import com.gws.dto.OperationResult;
import com.gws.enums.BizErrorCode;
import com.gws.services.oss.AliossService;
import com.gws.utils.GwsLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.jboss.logging.Logger;

/**
 * 阿里Oss实现类
 */
@Configuration
@Service
public class AliossServiceImpl implements AliossService{

    static Logger logger = Logger.getLogger(GwsWebApplication.class);

    @Value("${ali.oss.endpoint}")
    private String aliOssEndpoint;

    @Value("${ali.accessKey}")
    private String aliAccessKey;

    @Value("${ali.secretKey}")
    private String aliSecretKey;

    @Value("${ali.oss.cdnHttpsHost}")
    private String cdnHttpsHost;

    private String http = "http://";

    @Value("${file.folder}")
    private String fileFolder;

    @PostConstruct
    public void init() {
        logger.info("ossClient Started");
    }

    /**
     * 上传单个文件
     *
     * @param file   文件
     * @param bucket 存储空间
     * @return
     */
    @Override
    public String uploadFile(MultipartFile file, String bucket) {

        if (file.isEmpty()) {
            return null;
        }

        StringBuilder key = new StringBuilder();

        String fileName = file.getOriginalFilename();
        String postfix = getPostfix(fileName);

        if (!StringUtils.isEmpty(postfix)) {
            key.append(postfix).append("/");
        }
        key.append(UUID.randomUUID().toString());
        if (!StringUtils.isEmpty(postfix)) {
            key.append(".").append(postfix);
        }
        String fixKey = String.valueOf(key);
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(aliOssEndpoint, aliAccessKey, aliSecretKey);

        try {
            ossClient.putObject(bucket, fixKey, file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 关闭client
        ossClient.shutdown();

        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer downCdnHttpsHost = stringBuffer.append(http).append(bucket).append(cdnHttpsHost);

        return new StringBuffer().append(downCdnHttpsHost).append("/").append(key).toString();
    }

    /**
     * 上传一个字符串
     *
     * @param file
     * @param bucket
     * @return
     */
    @Override
    public String uploadStringFile(String file, String bucket) {
        if (StringUtils.isEmpty(file) || StringUtils.isEmpty(bucket)) {
            return null;
        }

        StringBuilder key = new StringBuilder();

        String fileName = file;
        String postfix = getPostfix(fileName);

        if (!StringUtils.isEmpty(postfix)) {
            key.append(postfix).append("/");
        }
        key.append(UUID.randomUUID().toString());
        if (!StringUtils.isEmpty(postfix)) {
            key.append(".").append(postfix);
        }
        String fixKey = String.valueOf(key);
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(aliOssEndpoint, aliAccessKey, aliSecretKey);

        ossClient.putObject(bucket, fixKey, new ByteArrayInputStream(file.getBytes()));
        // 关闭client
        ossClient.shutdown();

        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer downCdnHttpsHost = stringBuffer.append(http).append(bucket).append(cdnHttpsHost);

        return new StringBuffer().append(downCdnHttpsHost).append("/").append(key).toString();
    }

    /**
     * 流式下载
     *
     * @param key
     * @return
     */
    @Override
    public InputStream downByStream(String bucket,String key) throws IOException {
        // 创建OSSClient实例
        OSSClient ossClient = new OSSClient(aliOssEndpoint, aliAccessKey, aliSecretKey);
        OSSObject ossObject = ossClient.getObject(bucket, key);

        // 读Object内容
        System.out.println("Object content:");
        InputStream inputStream = ossObject.getObjectContent();
        //关闭流
        inputStream.close();
        // 关闭client
        ossClient.shutdown();
        return inputStream;
    }

    /**
     * 批量上传
     * 上传多个文件
     *
     * @param files
     * @param bucket
     * @return
     */
    @Override
    public List<String> uploadFiles(MultipartFile[] files, String bucket) {

        if (null == files || StringUtils.isEmpty(bucket)){
            return Collections.EMPTY_LIST;
        }

        List<String> result = new ArrayList<>();

        for (MultipartFile file : files){
            String download = uploadFile(file,bucket);
            result.add(download);
        }

        return CollectionUtils.isEmpty(result) ? Collections.EMPTY_LIST : result;
    }

    /**
     * 文件六传输
     *
     * @param inputStream
     * @param fileName
     * @return
     */
    @Override
    public OperationResult<Boolean> uploadFileToBasetool(InputStream inputStream, String fileName) throws FileNotFoundException {
        if (null == inputStream) {
            return new OperationResult<>(BizErrorCode.PARM_ERROR);
        }
        String saveAsPath = fileFolder + fileName;
        File file = new File(saveAsPath);

        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
        return null;
    }

    @Override
    public boolean download(String downloadUrl, String saveAsPath) {

        InputStream inputStream = null;
        OutputStream outputStream = null;

        try {
            File file = new File(saveAsPath);
            if (file.exists()) {
                GwsLogger.info("file {} already exist", saveAsPath);
                file.delete();
//                return true;
            }
            file.createNewFile();

            URL url = new URL(downloadUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            inputStream = conn.getInputStream();
            outputStream = new BufferedOutputStream(new FileOutputStream(file));
            int bufSize = 1024 * 4;
            byte[] buffer = new byte[bufSize];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, bytesRead);
            }
            return true;
        } catch (Exception e) {
            GwsLogger.error(e, "download {} error");
            return false;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getPostfix (String file){
        if (null == file) {
            return "";
        }
        int postfixIdx = file.lastIndexOf(".");
        if (-1 == postfixIdx) {
            return "";
        } else {
            return file.substring(postfixIdx + 1);
        }
    }
}
