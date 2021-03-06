package com.chenlei.web.common.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by water on 2016/7/16.
 */

//@Profile(value = "dev")
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("dev")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.rrkd.evaluate.service.controller"))
                .build();
    }

    @SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring boot REST Sample with Swagger")
                .description("Spring boot REST Sample with Swagger")
                .termsOfServiceUrl("")
                .contact("waterlang")
                .license("我的github")//链接上的文字
                .licenseUrl("https://github.com/waterlang")//链接
                .version("2.0")
                .build();
    }
}
