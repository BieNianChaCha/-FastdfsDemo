package com.example.fastdfsdemo.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Package： com.example.fastdfsdemo.service
 * @Author: zhangchengjia
 * @Since: 2020/3/17 11:38
 * @Version: V1.0
 */
@Service
public class FileService {

    private StorageClient1 getStorageClient(){
        try {
            ClientGlobal.initByProperties("application.properties");
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getTrackerServer();
            StorageServer storeStorage = trackerClient.getStoreStorage(trackerServer);
            StorageClient1 storageClient = new StorageClient1(trackerServer, storeStorage);
            return storageClient;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray uploadFiles(MultipartFile[] files){
        JSONArray jsonArray = new JSONArray();
        Arrays.stream(files).forEach(it ->{
            jsonArray.add(uploadFile(it));
        });
        return jsonArray;
    }

    public JSONObject uploadFile(MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        String[] split = originalFilename.split("\\.");
        try {
            String s = getStorageClient().upload_file1(file.getBytes(), split[1], null);
            Object o = new Object();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("path",s);
            return jsonObject;
        } catch (IOException e) {
            return JSONObject.parseObject(e.getMessage());
        } catch (MyException e) {
            return JSONObject.parseObject(e.getMessage());
        }
    }

    public void downFile(HttpServletResponse response,String fileId){
        try {
            byte[] bytes = getStorageClient().download_file1(fileId);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}
