package org.ixkit.anvilj.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: base_member
 * @Author: jeecg-boot
 * @Date:   2021-07-30
 * @Version: V1.0
 */
@Configuration
@MapperScan(value={"org.ixkit.**.mapper*"})
public class BaseMemberConfig{

}
