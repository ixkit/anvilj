//package org.ixkit.anvilj.framework.runtime;
//
//import lombok.extern.slf4j.Slf4j;
//import org.ixkit.anvil.util.GuidUtil;
//import org.springframework.stereotype.Component;
//
///**
// * @class:DebugerCfg
// * @author: RobinZ iRobinZhang@hotmail.com
// * @date: 25/11/2021
// * @version:0.1.0
// * @purpose:
// */
//@Slf4j
//@Component
//public class Debugger {
//    private static final String tag ="🔍Debugger-"+ GuidUtil.getUuid();
//    private static Debugger instance ;
//
//    private boolean isDebugMode = true;
//
//    private Debugger(){
//        instance = this;
//       // log.debug("DebuggerCfg created:{}",tag);
//    }
//    public static Debugger getInstance(){
//        return instance;
//    }
//    public static boolean isDebug(){
//        return getInstance().isDebugMode;
//    }
//}
