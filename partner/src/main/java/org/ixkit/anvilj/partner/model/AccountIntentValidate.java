package org.ixkit.anvilj.partner.model;

import org.ixkit.anvilj.web.io.ValidateUtil;

import java.util.List;
import java.util.function.Consumer;

public class AccountIntentValidate {

    public static List<String>  validate(Account account, Consumer consumer){
       List x = ValidateUtil.validate(account);
       if (consumer != null){
           consumer.accept(x);
       }

       return x;
    }
    public static List<String>  validate(Account account ){
        return validate(account,null);
    }
}
