package org.ixkit.anvilj.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import org.springframework.context.annotation.Profile;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @class:CoreSwagger2Config
 * @author: RobinZ iRobinZhang@hotmail.com
 * @date: 30/07/2022
 * @version:0.1.0
 * @purpose:
 */
@Configuration
@Profile({"dev"})  // by Robin
public class JcgSwagger2Config {
    @Bean
    public Docket infrastructureApi() {
        String moduleCode = "jeecg";
        String moduleName = "基础设施模块";
        String[] basePackage = { "org.jeecg","org.jeecg.modules" };
        return MultiModuleSwaggerBuilder.docketOf(moduleCode, moduleName, basePackage);
    }
}
