package org.ixkit.anvilj.space.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("spring.redis")
public class RedisConfig4Embed {
    private String host;
    private int port;
}
