package com.elec.mall.job;

import com.elec.mall.pojo.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class DeleteImageJob {

    @Bean
    public Product createProduct() {
        return new Product();
    }

    @Scheduled(cron = "0 */1 * * * ?")
    public void deleteImage() {
        System.out.println("DeleteImageJob.deleteImage");

    }

}
