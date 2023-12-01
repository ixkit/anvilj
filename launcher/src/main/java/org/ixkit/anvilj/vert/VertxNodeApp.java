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
//
//
////import io.quarkus.runtime.annotations.QuarkusMain;
//
//import lombok.extern.slf4j.Slf4j;
//import org.ixkit.anvil.util.StringUtil;
//import org.ixkit.land.lang.Strings;
//import org.ixkit.xapp.config.VertxNodeConfig;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
//
//
//import org.springframework.boot.context.event.ApplicationReadyEvent;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.event.EventListener;
//import org.springframework.core.env.Environment;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
//
///**
// *
// */
//@Slf4j
//@Configuration
//
//@ComponentScan(basePackages = {"org.ixkit.xapp"
//        ,"org.jeecg"
//})
//@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
//@PropertySource(value={
//         "classpath:application.yml",
//         "classpath:application-dev.yml"}
//        ,factory=YamlPropertySourceFactory.class
//)
////@QuarkusMain
//// 使用 spring 容器方式启动 Vertx
//public class VertxNodeApp {
//  @Autowired
//  VertxNodeConfig config;
//
//
//  private static VertxNodeApp self;
//
//
//  public static void main(String[] args) {
//   boolean isConfigVertxApp = true;
//    ApplicationContext  context =  null;
//    if (isConfigVertxApp) {
//        context = new AnnotationConfigApplicationContext(VertxNodeApp.class);
//
//        self = (VertxNodeApp) context.getBean(Strings.lowerFirstClassName(VertxNodeApp.class));
//        self.start(context);
//    }
//
//  }
//
//  /*
//    Vertx -> NodeServer --> NodeManager --> Node ....
//  */
//  public void start(ApplicationContext  context){
//
//     log.debug("start :{}",context);
//  }
//
//    private void _onLoaded(ApplicationContext  context){
//        self = (VertxNodeApp)context.getBean(Strings.lowerFirstClassName(VertxNodeApp.class));
//
//        //VertxBot.startBot(context);
//       // VertxBot.deployNode(PushMessageNode.class);
//    }
//    @EventListener
//    public void onReady(ApplicationReadyEvent event) {
//        _onLoaded(event.getApplicationContext());
//
//        outContext(event.getApplicationContext());
//    }
//    //----------------------------------------------------------
//    private static void  outContext(ApplicationContext application){
//        try {
//
//            _outContext(application);
//        }catch (Exception ex){
//            log.error("outContext error:{}",ex);
//        }
//    }
//    private static void _outContext(ApplicationContext application) throws UnknownHostException {
//        Environment env = application.getEnvironment();
//        String ip = InetAddress.getLocalHost().getHostAddress();
//        String port = env.getProperty("server.port");
//        String vertxPort = self.config.getPort()+"";
//        String path = StringUtil.getString(env.getProperty("server.servlet.context-path"));
//        String appName = VertxNodeApp.class.getSimpleName();
//        log.info("\n----------------------------------------------------------\n\t" +
//                appName +" 🏎 🏎 🏎  is running! Access URLs:\n\t" +
//                "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
//                "External: \thttp://" + ip + ":" + port + path + "/\n\t" +
//                "Vert.x  : \thttp://" + ip + ":" + vertxPort + path + "/\n\t" +
//                "----------------------------------------------------------");
//    }
//
//
//}
