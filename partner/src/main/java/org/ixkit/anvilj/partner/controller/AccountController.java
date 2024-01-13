package org.ixkit.anvilj.partner.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.ixkit.anvilj.core.Primitive;
import org.ixkit.anvilj.partner.entity.Partner;
import org.ixkit.anvilj.partner.model.Account;
import org.ixkit.anvilj.partner.model.AccountIntentValidate;
import org.ixkit.anvilj.partner.model.SignData;
import org.ixkit.anvilj.partner.service.IAccountService;
import org.ixkit.anvilj.web.io.Output;
import org.ixkit.anvilj.web.io.ValidateUtil;
import org.ixkit.land.lang.Argument;
import org.ixkit.land.lang.Lists;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CacheConstant;
import org.jeecg.common.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Slf4j
@Api(tags="平台用户账户")
@RequestMapping("/api/v1/partner/account")
@RestController
public class AccountController {

    @Autowired
    private IAccountService accountService;

   @ApiOperation(value="用户注册 申请开户", notes="用户注册 申请开户")
   @PostMapping(value = "/register")
   public Result<Object> register(@RequestBody Account account,
                                  HttpServletRequest req) {
       try {
           List errors = AccountIntentValidate.validate(account);
           if (!Lists.isEmpty(errors)){
               return Result.error("无效参数!",errors);
           }
           accountService.register(account);
           Object response = account.get(Primitive.response);
           if (null!= response) return  Result.ok(response);
           return Result.OK();
       }catch (Exception ex){
           ex.printStackTrace();
           Argument error = Output.toErrorResponse(ex);
           return Result.error("注册有误，请稍后再试",error);
       }


   }


    @ApiOperation(value="用户签注 确认开户", notes="用户签注 确认开户")
    @GetMapping(value = "/sign")
    public Result<Object> sign(SignData signData,
                               HttpServletRequest req) {

        try {

            List errors =  ValidateUtil.validate(signData);;
            if (!Lists.isEmpty(errors)){
                return Result.error("无效参数!",errors);
            }
            Partner partner = accountService.sign(signData);
            if (null != partner){
                String message = "Congratulation! Your account is active, you can login now!";
                return Result.OK(message,null);
            }
            return Result.error("签注有误，请稍后再试");

        }catch (Exception ex){
            ex.printStackTrace();
            Argument error = Output.toErrorResponse(ex);
            return Result.error("签注有误，请稍后再试",error);
        }
    }

}
