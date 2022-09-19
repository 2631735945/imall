package com.elec.mall;


import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

// 声明该类是一个SpringBoot的引导类
@SpringBootApplication
// @MapperScan注解，扫描MyBatis Mapper接口类
@MapperScan("com.elec.mall.mapper")
//开启quartz定时任务
@EnableScheduling
public class AdminApplication {
    private static final Logger LOG = LoggerFactory.getLogger(AdminApplication.class);

    public static void main(String[] args) {
        // SpringApplication.run(WikiApplication.class, args);
        SpringApplication app = new SpringApplication(AdminApplication.class);
        Environment environment = app.run(args).getEnvironment();
        LOG.info("启动成功");
        LOG.info("地址:\thttp://127.0.0.1:{}", environment.getProperty("server.port"));
    }


}
