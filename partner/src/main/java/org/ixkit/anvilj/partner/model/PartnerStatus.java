package org.ixkit.anvilj.partner.model;

public enum PartnerStatus {
    fresh(),
    applyEnroll(),
    pendingSign(),
    expireSign(),
    signed(),
    approved(),
    normal(),
    forbid(),
    removed(),
    ;

    public String getCode(){
        return this.name();
    }
}
