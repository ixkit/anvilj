package org.ixkit.anvilj.partner.service;

import org.ixkit.anvilj.partner.entity.Partner;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 基础资源 成员表
 * @Author: jeecg-boot
 * @Date:   2024-01-02
 * @Version: V1.0
 */
public interface IPartnerService extends IService<Partner> {

    public boolean create(Partner partner);
}
