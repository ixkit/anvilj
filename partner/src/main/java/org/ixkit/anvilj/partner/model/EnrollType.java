package org.ixkit.anvilj.partner.model;

import org.ixkit.anvilj.framework.runtime.AppEnv;
import org.ixkit.anvilj.partner.service.IEnrollStepService;
import org.ixkit.anvilj.partner.service.impl.EmailRegisterService;

public enum EnrollType {
    email(),
    sms(),
    free()

    ;
    public IEnrollStepService getService(){
        if (this.equals(email)){
            return AppEnv.current.getBean(EmailRegisterService.class);
        }
        return null;
    }
}
