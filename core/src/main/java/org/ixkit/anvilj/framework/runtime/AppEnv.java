package org.ixkit.anvilj.framework.runtime;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.core.env.Environment;

import java.util.Arrays;


/**
 * @class:AppEnv
 * @author: RobinZ iRobinZhang@hotmail.com
 * @date: 16/12/2021
 * @version:0.1.0
 * @purpose:
 */
public enum AppEnv {
        current;

//    @Autowired
    Environment env;
    private ApplicationContext applicationContext;

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public boolean isDebugMode(){
        env = this.applicationContext.getBean(Environment.class);
        String[] profiles = env.getActiveProfiles();
        return containsDevProfile(profiles);
    }
    private boolean containsDevProfile(String[] profiles){
        if(Arrays.stream(profiles).anyMatch(
                env -> (env.equalsIgnoreCase("dev")) ))
        {
            return true;
        }
        return false;
    }

    public <T> T getBean(Class<T> clazz){
        try {

            return this.applicationContext.getBean(clazz);
        }
        catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException){
            //do nothing
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    public <T> T getBean(String beanName,Class<T> clazz){
        return  this.applicationContext.getBean(beanName,clazz);
    }

    public void postEvent(ApplicationEvent event){
        this.getApplicationContext().publishEvent(event);
    }


    static  {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("\n\n\n bye ....👋 \n\n\n");
            }
        });
    }
}
