package org.ixkit.anvilj.framework;

import org.ixkit.anvilj.framework.runtime.AppEnv;

/**
 * @class:Services
 * @author: RobinZ iRobinZhang@hotmail.com
 * @date: 11/07/2022
 * @version:0.1.0
 * @purpose:
 */
public class Services {

    public static  <T> T seek(Class<T> t){
       return AppEnv.current.getBean(t);
    }


}
