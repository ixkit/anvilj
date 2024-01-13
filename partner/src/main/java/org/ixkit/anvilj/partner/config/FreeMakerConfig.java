package org.ixkit.anvilj.partner.config;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
@org.springframework.context.annotation.Configuration
public class FreeMakerConfig {

    public MyFreeMakerConfiguration getTemplateCfg() throws Exception {
        try{
            return _getTemplateCfg();
        }catch (Exception ex){
            ex.printStackTrace();
            log.warn("Failed init FreeMakerConfig,error:{}",ex.toString());
        }
        return  null;
    }
    private MyFreeMakerConfiguration _getTemplateCfg() throws Exception{
        MyFreeMakerConfiguration configuration = new MyFreeMakerConfiguration(Configuration.getVersion());
        // 设置模板路径 toURI()防止路径出现空格
        String classpath = this.getClass().getResource("/").toURI().getPath();
        configuration.setDirectoryForTemplateLoading(new File(classpath+"/partner/"));
        // 设置字符集
        configuration.setDefaultEncoding("utf-8");
        // 加载模板
        return  configuration;
    }




    public Template getTemplate(String fileName)  {
        try{
            Template template = getTemplateCfg().getTemplate(fileName);
            return template;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}
