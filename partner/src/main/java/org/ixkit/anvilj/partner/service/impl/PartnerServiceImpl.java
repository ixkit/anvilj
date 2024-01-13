package org.ixkit.anvilj.partner.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ixkit.anvilj.partner.entity.Partner;
import org.ixkit.anvilj.partner.mapper.PartnerMapper;
import org.ixkit.anvilj.partner.service.IPartnerService;
import org.ixkit.land.lang.X;
import org.jeecg.modules.system.entity.SysUser;
import org.jeecg.modules.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description: 基础资源 成员表
 * @Author: jeecg-boot
 * @Date: 2024-01-02
 * @Version: V1.0
 */
@Service
public class PartnerServiceImpl extends ServiceImpl<PartnerMapper, Partner> implements IPartnerService {

    @Autowired
    private ISysUserService sysUserService;


    @Override
    public boolean create(Partner partner) {
        SysUser sysUser = createSysUser(partner);
        if (null == sysUser) {
            return false;
        }

        partner.setRefSysUserId(sysUser.getId());
        return save(partner);
    }

    private SysUser createSysUser(Partner partner) {
        SysUser sysUser = new SysUser();
        X.with(sysUser, (x) -> {
            x.setUsername(partner.getName());
            x.setPhone(partner.getPhone());
            x.setEmail(partner.getEmail());
            x.setPassword(partner.getAccount().getPassword());
            x.setSalt(partner.getAccount().getSalt());
            x.setStatus(1);
            x.setDelFlag(0);
        });
        String defaultRoleIds = partner.getAccount().getRoleIds();

        sysUserService.saveUser(sysUser, defaultRoleIds, null, null);

        return sysUser;
    }


}
