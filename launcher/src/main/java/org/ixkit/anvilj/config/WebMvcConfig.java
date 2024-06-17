package org.ixkit.anvilj.config;

import org.jeecg.config.MvcDef;
import org.jeecg.config.MvcMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebMvcConfig {


    @Bean
    @MvcMapping
    public Map indexMvcMapping() {
        Map result = new HashMap();
        MvcDef root = MvcDef.create("/","index.html",999);
        result.put(root.getPath(),root);
        return result;
    }
}
