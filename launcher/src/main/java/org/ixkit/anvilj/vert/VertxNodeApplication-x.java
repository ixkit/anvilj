///*
// * Copyright 2017 Red Hat, Inc.
// *
// * Red Hat licenses this file to you under the Apache License, version 2.0
// * (the "License"); you may not use this file except in compliance with the
// * License.  You may obtain a copy of the License at:
// *
// * http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
// * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
// * License for the specific language governing permissions and limitations
// * under the License.
// */
//
//package org.ixkit.xapp.vert;
//
//import io.bq.power.base.util.Asyncer;
//import io.bq.power.base.util.StringUtil;
//import io.bq.power.land.Strings;
//import io.bq.power.rpc.launcher.config.VertxNodeConfig;
//import io.bq.power.rpc.vertx.factory.SpringVerticleFactory;
//import io.bq.power.rpc.vertx.factory.VertxNodeServer;
//import io.quarkus.runtime.annotations.QuarkusMain;
//import io.vertx.core.DeploymentOptions;
//import io.vertx.core.Vertx;
//import lombok.extern.slf4j.Slf4j;
//import org.ixkit.xapp.config.VertxNodeConfig;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.WebApplicationType;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.context.event.EventListener;
//import org.springframework.core.env.Environment;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
///**
// *
// */
//@Slf4j
//@SpringBootApplication
//@ComponentScan(basePackages = {"org.ixkit.xapp"
//        ,"org.jeecg"
//})
//@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
//@QuarkusMain
//public class VertxNodeApplication {
//  @Autowired
//  VertxNodeConfig config;
//
//
//  private static VertxNodeApplication self;
//
//
//  public static void main(String[] args) {
//
//    //AnnotationConfigApplicationContext
//    ApplicationContext  context = //  new AnnotationConfigApplicationContext(VertxNodeApplication.class);
//
//    // SpringApplication.run(VertxNodeApplication.class, args);
//   new SpringApplicationBuilder(VertxNodeApplication.class).web(WebApplicationType.NONE) // .REACTIVE, .SERVLET
//    .run(args);
//
//    self = (VertxNodeApplication)context.getBean(Strings.lowerFirstClassName(VertxNodeApplication.class));
//    self.start(context);
//
//
//  }
//  /*
//    Vertx -> NodeServer --> NodeManager --> Node ....
//  */
//  public void start(ApplicationContext  context){
//
//    Vertx vertx = Vertx.vertx();
//    SpringVerticleFactory verticleFactory = context.getBean(SpringVerticleFactory.class);
//    verticleFactory.setApplicationContext(context);
//    // The verticle factory is registered manually because it is created by the Spring container
//    vertx.registerVerticleFactory(verticleFactory);
//
//    // Scale the verticles on cores: create 4 instances during the deployment
//    DeploymentOptions options = new DeploymentOptions().setInstances(config.getCountOfInstance());
//    vertx.deployVerticle(verticleFactory.prefix() + ":" + VertxNodeServer.class.getName(), options);
//
//    Asyncer.run(2,()->{
//      outContext(context);
//    });
//  }
//
//
//  /**
//   * context publish events and then that application
//   * is ready to receive request
//   * The source of the event is the SpringApplication itself
//   * invoking after spring initialized
//   */
//  @EventListener
//  public void onContextRefreshed(ContextRefreshedEvent event) {
//    log.debug("onContextRefreshed:{}",event);
//  }
//  //@PostConstruct
//  @EventListener
//  public void onReady(ApplicationReadyEvent event) {
//    outContext(event.getApplicationContext());
//  }
//  //----------------------------------------------------------
//  private static void  outContext(ApplicationContext application){
//    try {
//
//      _outContext(application);
//    }catch (Exception ex){
//      log.error("outContext error:{}",ex);
//    }
//  }
//  private static void _outContext(ApplicationContext application) throws UnknownHostException {
//    Environment env = application.getEnvironment();
//    String ip = InetAddress.getLocalHost().getHostAddress();
//    String port = env.getProperty("server.port");
//    String vertxPort = self.config.getPort()+"";
//    String path = StringUtil.getString(env.getProperty("server.servlet.context-path"));
//    log.info("\n----------------------------------------------------------\n\t" +
//            "Transmitter 🏎 🏎 🏎 🏎 🏎 is running! Access URLs:\n\t" +
//            "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
//            "External: \thttp://" + ip + ":" + port + path + "/\n\t" +
//            "Vert.x  : \thttp://" + ip + ":" + vertxPort + path + "/\n\t" +
//            "----------------------------------------------------------");
//  }
//
//
//}
