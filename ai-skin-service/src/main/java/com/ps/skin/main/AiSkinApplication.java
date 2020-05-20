package com.ps.skin.main;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 项目启动类
 *
 * @author liuhj
 * @date 2020/05/18 20:48
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.ps.skin.controller", "com.ps.skin.config", "com.ps.skin.dao", "com.ps.skin.service"})
@Slf4j
@MapperScan("com.ps.skin.dao.mapper")
public class AiSkinApplication {
    public static void main(String[] args) {
        SpringApplication.run(AiSkinApplication.class, args);
        log.info("================AiSkinApplication Run SUCCESS===========");
    }
}
