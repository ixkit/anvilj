package org.ixkit.anvilj.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

 
@Configuration
@MapperScan(value={"org.ixkit.**.mapper*"})
public class BasePartnerConfig{

}
