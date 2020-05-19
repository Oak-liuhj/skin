package com.ps.skin.config;

import com.ps.skin.handler.GlobalInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * 拦截器配置
 *
 * @author liuhj
 * @date 2020/05/18 20:48
 */
@Configuration
public class MyInterceptorConfigurer extends WebMvcConfigurerAdapter {

    /**
     * 关键，将拦截器作为bean写入配置中，不能直接new,否则拦截器无法注入服务
     */
    @Bean
    public GlobalInterceptor globalInterceptor() {
        return new GlobalInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(globalInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/HttpInvoker/RecordSyncService")
                .excludePathPatterns("/HttpInvoker/RecordSyncService")
                // add for modality worklist query
                .excludePathPatterns("/works/v1/mwl/**")
                .excludePathPatterns("/works/v1/weixin/**")
                .excludePathPatterns("/works/v1/third/**")

                .excludePathPatterns("/v2/api-docs")
                .excludePathPatterns("/configuration/ui")
                .excludePathPatterns("/swagger-resources")
                .excludePathPatterns("/configuration/security")
                .excludePathPatterns("/swagger-ui.html")
                .excludePathPatterns("/webjars/**")
                .excludePathPatterns("/doc.html")

                .excludePathPatterns("/works/v1/logoutCallback")
                .excludePathPatterns("/doc.html");

        super.addInterceptors(registry);
    }
}