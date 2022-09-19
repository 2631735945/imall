package com.elec.mall.job;

import com.elec.mall.constant.RedisConstant;
import com.elec.mall.pojo.Product;
import com.elec.mall.util.QiniuUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

@Configuration
public class DeleteImageJob {
    @Autowired
    private RedisTemplate redisTemplate;

    @Bean
    public Product createProduct() {
        return new Product();
    }

    @Scheduled(cron = "0 */1 * * * ?")
    public void deleteImage() {
        System.out.println("DeleteImageJob.deleteImage");
        // 根据Redis里面保存的两个Set集合进行差值运算，获得没有使用的图片的集合
        Set<String> set = redisTemplate.opsForSet().difference(RedisConstant.UPLOAD_IMAGE, RedisConstant.UPLOAD_IMAGE_TO_DB);
        if (set != null) {
            for (String imageName : set) {
                // 删除七牛云上没有使用的图片
                QiniuUtils.deleteFileFromQiniu(imageName);
                //从Redis集合中删除保存在UPLOAD_IMAGE这个set中没有用的图片
                redisTemplate.opsForSet().remove(RedisConstant.UPLOAD_IMAGE, imageName);
                System.out.println("删除图片：" + imageName);

            }
        }


    }

}
