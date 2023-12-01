/*
 * Copyright 2017 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package org.ixkit.anvilj.vert;

import lombok.extern.slf4j.Slf4j;

import org.ixkit.anvil.lang.Strings;
import org.ixkit.anvil.util.StringUtil;
//import org.ixkit.framework.runtime.Env;
//import org.ixkit.vertxbot.VertxBot;
//import org.ixkit.vertxbot.config.VertxNodeConfig;
import org.ixkit.anvilj.launcher.RunArgument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 */
@Slf4j
@Configuration
@EnableScheduling
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
@ComponentScan(basePackages = {
        "org.ixkit",
        "org.ixkit.xapp"
        ,"org.jeecg"
})

public class VertxNodeApplication {
  //@Autowired
  //VertxNodeConfig config;

  @Autowired
  RunArgument runArgument;

  private static VertxNodeApplication self;


  public static void main(String[] args) {

    //AnnotationConfigApplicationContext
    ApplicationContext  context = //  new AnnotationConfigApplicationContext(VertxNodeApplication.class);

    // SpringApplication.run(VertxNodeApplication.class, args);
   new SpringApplicationBuilder(VertxNodeApplication.class).web(WebApplicationType.NONE) // .REACTIVE, .SERVLET
    .run(args);


  }
  private void _onLoaded(ApplicationContext  context){
    self = (VertxNodeApplication)context.getBean(Strings.lowerFirstClassName(VertxNodeApplication.class));

    //VertxBot.startBot(context);
  //  VertxBot.deployNode(PushMessageNode.class);
  }



  /**
   * context publish events and then that application
   * is ready to receive request
   * The source of the event is the SpringApplication itself
   * invoking after spring initialized
   */
 /// @EventListener
  public void onContextRefreshed(ContextRefreshedEvent event) {
    log.debug("onContextRefreshed:{}",event);
  }
  //@PostConstruct
 /// @EventListener
  public void onReady(ApplicationReadyEvent event) {
    _onLoaded( event.getApplicationContext());
    outContext(event.getApplicationContext());
  }

  //----------------------------------------------------------
  private static void  outContext(ApplicationContext application){
    try {

      _outContext(application);
    }catch (Exception ex){
      log.error("outContext error:{}",ex);
    }
  }
  private static void _outContext(ApplicationContext application) throws UnknownHostException {
    Environment env = application.getEnvironment();
    String ip = InetAddress.getLocalHost().getHostAddress();
    String port = env.getProperty("server.port");
    String vertxPort = "";//self.config.getPort()+"";
    String path = StringUtil.getString(env.getProperty("server.servlet.context-path"));
    String appName = VertxNodeApplication.class.getSimpleName();
    log.info("\n----------------------------------------------------------\n\t" +
            appName +" 🏎 🏎 🏎  is running! Access URLs:\n\t" +
            "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
            "External: \thttp://" + ip + ":" + port + path + "/\n\t" +
            "Vert.x  : \thttp://" + ip + ":" + vertxPort + path + "/\n\t" +
            "----------------------------------------------------------");
  }


}
