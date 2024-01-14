package org.ixkit.anvilj.website.config;

import com.samskivert.mustache.Mustache;
import org.ixkit.land.utils.JsonUtil;
import org.springframework.boot.autoconfigure.mustache.MustacheEnvironmentCollector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class WebSiteConfig {
    @Bean
    public Mustache.Compiler mustacheCompiler(
            Mustache.TemplateLoader templateLoader,
            Environment environment) {

        MustacheEnvironmentCollector collector
                = new MustacheEnvironmentCollector();
         environment.getActiveProfiles();
        return Mustache.compiler()
                .defaultValue("")
                .withLoader(templateLoader)
                .withCollector(collector);
    }
}
