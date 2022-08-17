/**
 * AllrightsReserved,DesignedBywww.maihaoche.com
 * Package com.volunteer.demo
 * author: shengqiang (shengqiang@maihaoche.com)
 * date:  2018/8/9下午1:52
 * Copyright:2017-2018www.maihaoche.comInc.Allrightsreserved.
 * 注意：本内容仅限于卖好车内部传阅，禁止外泄以及用于其他的商业目的
 */
package com.volunteer.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Description: ${TODO}
 * author: shengqiang (shengqiang@maihaoche.com)
 * date: 2018/8/9下午1:52
 * sinceV1.0
 */
@Configuration
@EnableSwagger2
public class Swagger {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("shengqiang")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.volunteer.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/").setCachePeriod(0);
    }




    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("volunteer")
                .description("志愿者工作站接口文档")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }
}
