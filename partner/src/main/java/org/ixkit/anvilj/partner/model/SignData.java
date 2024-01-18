package org.ixkit.anvilj.partner.model;

import lombok.Data;
import org.ixkit.anvilj.core.XObject;
import org.ixkit.land.lang.Argument;

import javax.validation.constraints.NotBlank;

@Data
public class SignData extends XObject {
    @NotBlank(message = "帐户名称不能为空")
    private String uid;

    @NotBlank(message = "token不能为空")
    private String token;

    @NotBlank(message = "ts不能为空")
    private String ts;

    // identify the redirect link while sign operation done
    private String redirectLink;

    public static Argument toArgs(SignData signData){
        //TODO blank character ?
        return Argument.of("uid", signData.getUid(),"token",signData.getToken(), "ts",signData.getTs(),"redirectLink", signData.getRedirectLink());
    }
}
