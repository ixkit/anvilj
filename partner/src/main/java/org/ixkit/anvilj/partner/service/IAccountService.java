package org.ixkit.anvilj.partner.service;

import org.ixkit.anvilj.partner.entity.Partner;
import org.ixkit.anvilj.partner.model.IAccount;
import org.ixkit.anvilj.partner.model.SignData;

public interface IAccountService {

    public IAccount register(IAccount input) throws Exception;

    public Partner sign(SignData input) throws Exception;
}
