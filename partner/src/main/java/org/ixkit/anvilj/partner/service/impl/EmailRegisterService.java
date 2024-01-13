package org.ixkit.anvilj.partner.service.impl;

import cn.hutool.core.lang.UUID;
import com.google.common.base.Joiner;
import org.ixkit.anvilj.partner.config.RegisterConfig;
import org.ixkit.anvilj.partner.model.Account;
import org.ixkit.anvilj.partner.model.IAccount;
import org.ixkit.anvilj.partner.model.SignData;
import org.ixkit.anvilj.partner.notify.NotifyContentBuilder;
import org.ixkit.anvilj.partner.service.IEnrollStepService;
import org.ixkit.land.lang.Argument;
import org.ixkit.land.lang.DateTimes;
import org.jeecg.modules.message.handle.impl.EmailSendMsgHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailRegisterService implements IEnrollStepService {

    @Autowired
    private EmailSendMsgHandle sendMsgHandle;

    @Autowired
    private RegisterConfig registerConfig;

    public void sendSignEmail(Account account){
        String target = account.getEmail();
        SignData signData = new SignData();
        signData.setUid(account.getName());
        generateSignToken(signData);
        String signParams = map2RequestParams(SignData.toArgs(signData));

        Argument context = Argument.of();
        context.put(NotifyContentBuilder.Keys.signParams.name(),signParams);
        context.put(NotifyContentBuilder.Keys.endPoint.name(),registerConfig.endPoint);
        account.put("context",context);
        account.put(SignData.class.getName(),signData);

        String content = NotifyContentBuilder.toVerifyEmail(account,context);
        String title = registerConfig.titleOfSignEmail;
        sendMsgHandle.sendMsg( target, title, content);

    }
    private String map2RequestParams(Map map){
        String result = Joiner.on("&").withKeyValueSeparator("=")
                .join(map);
        return result;
    }
    private SignData generateSignToken(SignData signData){
        String token = UUID.fastUUID().toString(true);
        signData.setToken(token);
        String ts = DateTimes.getTimestamp() + "";
        signData.setTs(ts);
        return signData;
    }

    @Override
    public void notifySign(IAccount account) {
        sendSignEmail((Account) account);
    }

    @Override
    public void userSignature(IAccount account) {

    }
}
