package org.ixkit.anvilj.config;

import io.swagger.annotations.ApiOperation;
import org.jeecg.common.constant.CommonConstant;

import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Swagger2配置工具类
 *
 * @author CL
 *
 */
//@Component
//@Profile({"dev"})  // by Robin
//@EnableSwagger2
public class MultiModuleSwaggerBuilder {

    public static Docket docketOf(String moduleCode, String moduleName, String... basePackage) {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo(moduleName))
                .groupName(moduleCode)
                .select()
                //此包路径下的类，才生成接口文档
                //RequestHandlerSelectors.basePackage("org.jeecg")
                .apis(
                        SwaggerUtils.basePackages(basePackage)
                )
                //加了ApiOperation注解的类，才生成接口文档
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Collections.singletonList(securityScheme()))
                .securityContexts(securityContexts());
        //.globalOperationParameters(setHeaderToken());
    }

    /***
     * oauth2配置
     * 需要增加swagger授权回调地址
     * http://localhost:8888/webjars/springfox-swagger-ui/o2c.html
     * @return
     */
   // @Bean
    static SecurityScheme securityScheme() {
        return new ApiKey(CommonConstant.X_ACCESS_TOKEN, CommonConstant.X_ACCESS_TOKEN, "header");
    }

    /**
     * 新增 securityContexts 保持登录状态
     */
    private static List<SecurityContext> securityContexts() {
        return new ArrayList(
                Collections.singleton(SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build())
        );
    }

    private static List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return new ArrayList(
                Collections.singleton(new SecurityReference(CommonConstant.X_ACCESS_TOKEN, authorizationScopes)));
    }

    private static ApiInfo apiInfo(String moduleName) {
        return new ApiInfoBuilder().title(moduleName)
                .contact(new Contact("Powered by ixkit", "https://www.ixkit.org", null))
                .version("V1.0").build();
    }
}
