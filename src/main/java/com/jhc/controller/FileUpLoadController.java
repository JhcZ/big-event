package com.jhc.controller;

import com.jhc.pojo.Result;
import com.jhc.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @description: 该接口用于上传文件(单文件)
 * @author: JhcZ
 * @Email：2325947239@qq.com
 * @create: 2024-03-05 15:10
 **/

@RestController
public class FileUpLoadController {

    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) throws Exception {
        //获取文件的原始名称
        String originalFileName = file.getOriginalFilename();
        //上传文件时，可能会发生上传同一文件导致前者被覆盖，为了防止这种情况，可以使用UUID来确保文件名不会冲重复
        String fileName = UUID.randomUUID().toString() + originalFileName.substring(originalFileName.lastIndexOf("."));
        String url = AliOssUtil.uploadFile(fileName,file.getInputStream());

        return Result.success(url);
    }
}