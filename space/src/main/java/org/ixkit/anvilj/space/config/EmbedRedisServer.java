package org.ixkit.anvilj.space.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@RequiredArgsConstructor
@Profile("dev")
@Configuration
public class EmbedRedisServer {


    private final RedisConfig4Embed redisProperties;

    private RedisServer redisServer;

    @PostConstruct
    public void redisServer(){
        redisServer = new RedisServer(redisProperties.getPort());
        redisServer.start();
        log.info("Embed RedisServer Start!!!");
    }

    @PreDestroy
    public void stopRedis(){
        if(null != redisServer){
            redisServer.stop();
            log.info("Embed RedisServer Stop!!!");
        }
    }

}