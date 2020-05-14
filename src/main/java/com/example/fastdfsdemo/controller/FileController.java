package com.example.fastdfsdemo.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.fastdfsdemo.service.FileService;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Package： com.example.fastdfsdemo.controller
 * @Author: zhangchengjia
 * @Since: 2020/3/17 11:37
 * @Version: V1.0
 */
@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("upload")
    public JSONObject fileUpload(MultipartFile file){
        return fileService.uploadFile(file);
    }

    @PostMapping("down")
    public void fileDown(@RequestBody Map map, HttpServletResponse response){
        fileService.downFile(response, map.get("fileId").toString());
    }

    @PostMapping("uploads")
    public JSONArray fileUPloads(MultipartFile[] files){
        return fileService.uploadFiles(files);
    }
}
