package org.ixkit.anvilj.partner.service;

import org.ixkit.anvilj.partner.model.IAccount;

public interface IEnrollStepService {

    public void notifySign(IAccount account);

    public void userSignature(IAccount account);
}
