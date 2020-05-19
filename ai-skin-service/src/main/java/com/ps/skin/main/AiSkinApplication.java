package com.ps.skin.main;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 项目启动类
 *
 * @author liuhj
 * @date 2020/05/18 20:48
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@ComponentScan(basePackages = {"com.ps.skin.controller"})
@Slf4j
public class AiSkinApplication {
    public static void main(String[] args) {
        SpringApplication.run(AiSkinApplication.class, args);
        log.info("================AiSkinApplication Run SUCCESS===========");
    }
}
