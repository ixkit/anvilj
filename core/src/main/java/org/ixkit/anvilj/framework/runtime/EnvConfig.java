package org.ixkit.anvilj.framework.runtime;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @class:EnvConfig
 * @author: RobinZ iRobinZhang@hotmail.com
 * @date: 16/12/2021
 * @version:0.1.0
 * @purpose:
 */
@Slf4j
@Configuration
public class EnvConfig {
    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void init() {
        log.debug("init with applicationContext:{}",applicationContext);
        AppEnv.current.setApplicationContext(applicationContext);
    }
}
