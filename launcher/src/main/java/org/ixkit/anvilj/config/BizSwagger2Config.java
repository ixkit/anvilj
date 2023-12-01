package org.ixkit.anvilj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @class:BizSwagger2Config
 * @author: RobinZ iRobinZhang@hotmail.com
 * @date: 30/07/2022
 * @version:0.1.0
 * @purpose:
 */
@Configuration
@Profile({"dev"})  // by Robin
public class BizSwagger2Config {
    @Bean
    public Docket bizApi() {
        String moduleCode = "biz";
        String moduleName = "biz模块";
        String[] basePackage = { "org.ixkit" };
        return MultiModuleSwaggerBuilder.docketOf(moduleCode, moduleName, basePackage);
    }
}
