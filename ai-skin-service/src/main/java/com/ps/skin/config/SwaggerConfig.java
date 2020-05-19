package com.ps.skin.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

/**
 * 集成Swagger2工具配置
 *
 * @author liuhj
 * @date 2020/05/18 20:48
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {

    /**
     * 配置不同环境时是否启动swagger
     */
    @Value("${swagger.enable}")
    private boolean enableSwagger;

    /**
     * 全部API接口
     *
     * @return
     */
    @Bean
    public Docket allRestApi() {
        return buildDocket("V1.0", "com.ps.skin");
    }

    private Docket buildDocket(final String groupName, final String packageStr) {
        // Docket admin
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.groupName(groupName);
        docket.apiInfo(apiInfo());
        final ApiSelectorBuilder selectorBuilder = new ApiSelectorBuilder(docket);
        selectorBuilder.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class));
        selectorBuilder.paths(PathSelectors.any());
        docket = selectorBuilder.build();
        docket.globalOperationParameters(setHeaderToken());
        docket.enable(enableSwagger);
        return docket;
    }

    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("管理平台开发测试API文档")
                .description("Ris Center Team")
                .termsOfServiceUrl("")
                .contact(new Contact("", "", ""))
                .version("1.0")
                .build();
    }

    private List<Parameter> setHeaderToken() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("token").description("token").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());
        return pars;
    }


}
