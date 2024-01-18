package org.ixkit.anvilj.partner.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RegisterConfig {
    @Value("${register.emailSign.durationSeconds: 60}")
    public long emailSignDurationSeconds;

    @Value("${register.emailSign.title: Welcome!}")
    public String titleOfSignEmail;

    @Value("${register.emailSign.endPoint}")
    public String endPoint;

    @Value("${register.partner.roles}")
    public String defaultPartnerRoles;

    @Value("${register.partner.orgCode}")
    public String defaultPartnerOrgCode;

}
