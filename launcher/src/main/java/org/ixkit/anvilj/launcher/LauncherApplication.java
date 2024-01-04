package org.ixkit.anvilj.launcher;


import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.util.oConvertUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;


/**
* 单体启动类（采用此类启动为单体模式）
*/
@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {
        "org.ixkit"
        ,"org.jeecg"
        ,"org.jeecg.modules.demo.test.controller"
})
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class LauncherApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LauncherApplication.class);
    }

    public static void main(String[] args) throws UnknownHostException {
        System.out.println("---------run---------");
        ConfigurableApplicationContext application = SpringApplication.run(LauncherApplication.class, args);

        _printContext(application);
    }

    private static void _printContext(ApplicationContext application) throws UnknownHostException {
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = oConvertUtils.getString(env.getProperty("server.servlet.context-path"));
        String buf = "\n----------------------------------------------------------\n\t" +
                "Application AnvilJ is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
                "External: \thttp://" + ip + ":" + port + path + "/\n\t" +
                "Swagger文档: \thttp://" + ip + ":" + port + path + "/doc.html\n" +
                "----------------------------------------------------------";
        log.info(buf);

    }
}