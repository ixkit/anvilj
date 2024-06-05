package org.ixkit.anvilj.embed;

import com.github.microwww.redis.RedisServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;


import java.io.IOException;
import java.net.InetSocketAddress;

@Slf4j
public class Infrastructures {


    @Bean
    public RedisServer mockRedisServer(RedisProperties redisProperties) throws IOException {
        RedisServer server = new RedisServer();
        server.listener(redisProperties.getHost(), redisProperties.getPort());

        InetSocketAddress address = (InetSocketAddress)server.getSockets().getServerSocket().getLocalSocketAddress();
        log.info("Mocker Redis start :: [{}:{}], set 'server.redis.host' to match it", address.getHostName(), address.getPort());
        return server;
    }
}
