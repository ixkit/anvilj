package org.ixkit.anvilj.partner.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.ixkit.anvilj.partner.config.RegisterConfig;
import org.ixkit.anvilj.partner.entity.Partner;
import org.ixkit.anvilj.partner.model.Account;
import org.ixkit.anvilj.partner.model.EnrollType;
import org.ixkit.anvilj.partner.model.IAccount;
import org.ixkit.anvilj.partner.model.SignData;
import org.ixkit.anvilj.partner.service.IAccountService;
import org.ixkit.anvilj.partner.service.IPartnerService;
import org.ixkit.anvilj.web.exception.ServiceException;
import org.ixkit.land.lang.Lists;
import org.ixkit.land.lang.Strings;
import org.jeecg.common.constant.CacheConstant;
import org.jeecg.common.util.PasswordUtil;
import org.jeecg.common.util.RedisUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.system.entity.SysRole;
import org.jeecg.modules.system.service.ISysRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IPartnerService partnerService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RegisterConfig registerConfig;

    @Autowired
    private ISysRoleService sysRoleService;
    /*
      user apply enroll partner --> send verify sign email --> sign ---> done
     */
    @Override
    public IAccount register(IAccount input) throws Exception{
        Account account = (Account)input;

        ensureIsUnEnrolledPartner(account);

        account = encryptPassword(account);

        EnrollType enrollType = account.getEnrollType();
        enrollType.getService().notifySign(account);
        cacheApplyAccount(account);
        return account;
    }

    private Account encryptPassword(Account account){
        String salt = oConvertUtils.randomGen(8);
        account.setSalt(salt);
        String passwordEncode = PasswordUtil.encrypt(account.getName(), account.getPassword(), salt);
        account.setPassword(passwordEncode);
        return account;
    }

    private boolean ensureIsUnEnrolledPartner(Account account) throws ServiceException {
        List<Partner> partners = findPartners( account);
        if (!Lists.isEmpty(partners)){
             throw new ServiceException("Partner is Exist!");
        }
        if (existInCache(account)){
            throw new ServiceException("Please verify register steps in previous notify email:" + account.getEmail()  );
        }
        return true;
    }

    private  List<Partner> findPartners(Account account){
        LambdaQueryWrapper<Partner> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Partner::getName, account.getName()).or(x->{
            if (!Strings.isEmpty(account.getEmail())){
                 x.eq(Partner::getEmail, account.getEmail());
            }
        });
        List<Partner>  list = partnerService.list(wrapper);
        return list;
    }

    private boolean existInCache(Account account){
       Object value =  redisUtil.get(account.getName()) ;
       if (null != value){
           return true;
       }
       String email = account.getEmail();
       if (Strings.isEmpty(email)){
           return false;
       }
        value =  redisUtil.get(email) ;
        if (null != value){
            return true;
        }
        return false;
    }

    private boolean cacheApplyAccount(Account account){
        long expired = registerConfig.emailSignDurationSeconds;
        String userName = account.getName();
        redisUtil.set(userName,account, expired);
        String email = account.getEmail();
        if (!Strings.isEmpty(email)) {
            redisUtil.set(email,account,expired);
        }
        return true;
    }
    private void cleanCachedApplyAccount(Account account) {
        redisUtil.del(account.getName());
        if (null != account.getEmail()) {
            redisUtil.del(account.getEmail());
        }
    }
    private Account fetchApplyAccount(SignData signData){
       return (Account) redisUtil.get(signData.getUid());
    }

    @Override
    public Partner sign(SignData input) throws Exception{
        validateSignRequest(input);
        Account account = validateSignRequest(input);
        account.getEnrollType().getService().userSignature(account);
        Partner partner = createPartner(account);
        if (null!= partner){
            cleanCachedApplyAccount(account);
        }
        return partner;
    }
    private Partner createPartner(Account account){
        Partner partner = new Partner();
        BeanUtils.copyProperties(account,partner);

        account.setRoleIds(getDefaultRoleIds(registerConfig.defaultPartnerRoles));
        partner.setAccount(account);
        partnerService.create(partner);
        return partner;
    }

    private Account validateSignRequest(SignData input) throws Exception{
        Account account = fetchApplyAccount(input);
        if (null == account ){
            throw new ServiceException("No account was register or possible register session expired");
        }
        SignData existSignData = (SignData)account.get(SignData.class.getName());
        Assert.isTrue(existSignData.getUid().equals(input.getUid()),"invalidate uid");
        Assert.isTrue(existSignData.getToken().equals(input.getToken()),"invalidate token");
        Assert.isTrue(existSignData.getTs().equals(input.getTs()),"invalidate ts");
        return account;
    }


    private List<SysRole> findDefaultRoles(String roleCodes){
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        List names = Lists.of(roleCodes);
        wrapper.in(SysRole::getRoleName,names);
        List<SysRole> result = sysRoleService.list(wrapper);
        return result;
    }

    private String getDefaultRoleIds(String roleCodes){
        if (Strings.isEmpty(roleCodes)) return null;
        List<SysRole> roles = findDefaultRoles(roleCodes);
        if (null == roles || roles.size()<=0) return null;
        String result = roles.stream()
                .map((x)->{
                    return x.getId();
                })
                .collect(Collectors.joining(","));
        return result;
    }

}
