package com.elec.mall.controller;

import com.elec.mall.constant.RedisConstant;
import com.elec.mall.util.ImageServerUtil;
import com.elec.mall.util.JSONResult;
import com.elec.mall.util.QiniuUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private RedisTemplate redisTemplate;

    // SpringMVC会将上传的文件信息封装到MultipartFile
    @RequestMapping("/uploadImage")
    @ResponseBody
    public JSONResult uploadImage(MultipartFile file) {
        String name = UUID.randomUUID().toString().replace("-", "");
        String fileName = file.getOriginalFilename();
        System.out.println("fileName: " + fileName);//aa.jpg
        String extension = FilenameUtils.getExtension(fileName);//jpg
        String newFileName = name + "." + extension;//52635b3153cf415da70179a2c472b3b9.jpg
        if (ImageServerUtil.IMG_SERVER == ImageServerUtil.LOCAL) {
            String filePath = "D:\\mypic\\" + newFileName;//D:/mypic/52635b3153cf415da70179a2c472b3b9.jpg
            try {
                file.transferTo(new File(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                QiniuUtils.upload2Qiniu(file.getBytes(), newFileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        redisTemplate.opsForSet().add(RedisConstant.UPLOAD_IMAGE, newFileName);
        System.out.println("上传图片：" + newFileName);

        return JSONResult.ok("上传成功", newFileName);
    }

    public static void main(String[] args) {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);//9dbcb93e-1e6d-46c1-a0a2-faee5a71254e
        System.out.println(uuid.replace("-", ""));//52635b3153cf415da70179a2c472b3b9
    }
}
