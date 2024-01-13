package org.ixkit.anvilj.partner.notify;

import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.ixkit.anvilj.partner.model.Account;
import org.ixkit.land.lang.Argument;
import org.ixkit.land.lang.Buffers;
import org.jeecg.common.util.SpringContextUtils;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.ixkit.anvilj.partner.config.FreeMakerConfig;

@Slf4j
public class NotifyContentBuilder {
    public enum Keys{
        signParams,
        endPoint
            ;
    }
    private static String TagComment = "--@@";
    /*
    👏欢迎 ${name} 加入 ${community}!
    请点击链接完成注册流程～
    http:xxx/account/sign?${signParams}
    --@@ this line will be remove while send ~
    ${team}
     */
    public static String toVerifyEmail(Account account, Argument context){
        Argument argument = Argument.of();
        argument.put("name",account.getName());

        argument.putAll(context);
        String templateName = "register.verify.content.ftl";
        Template template = SpringContextUtils.getBean(FreeMakerConfig.class).getTemplate(templateName);
        if (null == template){
            log.warn("Failed load template:{}",templateName);
            return null;
        }

        try {
            String lines = FreeMarkerTemplateUtils.processTemplateIntoString(template, argument);
            lines = Buffers.trimLines(lines,TagComment);
            return lines;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        log.warn("Failed process template:{}",templateName);
        return null;
    }
}
