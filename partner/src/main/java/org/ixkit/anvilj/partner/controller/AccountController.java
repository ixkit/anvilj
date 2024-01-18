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
import org.ixkit.anvilj.web.io.Input;
import org.ixkit.anvilj.web.io.Output;
import org.ixkit.anvilj.web.io.ValidateUtil;
import org.ixkit.land.lang.Argument;
import org.ixkit.land.lang.Lists;
import org.ixkit.land.utils.JsonUtil;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CacheConstant;
import org.jeecg.common.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


@Slf4j
@Api(tags="平台用户账户")
@RequestMapping("/api/v1/partner/account")
@RestController
public class AccountController {

    @Autowired
    private IAccountService accountService;

   @ApiOperation(value="用户注册 申请开户", notes="用户注册 申请开户")
   @PostMapping(value = "/register")
   public Result<Object> register(@RequestBody  String bodyBuffer,
   HttpServletRequest req) {
       try {
            Account account = Input.deserializeXObject(bodyBuffer,Account.class);
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

    //@ApiOperation(value="用户签注 确认开户", notes="用户签注 确认开户")
   // @GetMapping(value = "/sign")
    public Result<Object> sign(SignData signData, HttpServletRequest req) {

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


    private Result verifySign(SignData signData,HttpServletRequest request){
        boolean mock = false;
        if (!mock){
            Result result = sign(signData,request);
            return result;
        }else{ // mockOnly
            Map params = Input.asParameters(request);
            boolean mockOK = null != params.get("mockOK");
            String info =  JsonUtil.map2JsonStr(params);
            if (mockOK) {
                return Result.ok("MockOK:" + info );
            }
            return Result.error("MockFailure:" + info);
        }
    }
    /*
     http://localhost:8181/anvilj/api/v1/partner/account/signView?&uid=robinz&token=0ea74a9fa8884069bbaf24c3bdb01869&ts=1705478993566
     */
    @ApiOperation(value="signView", notes="signView")
    @RequestMapping(value = "/signView",method = {RequestMethod.GET})
    public ModelAndView signView(SignData signData, HttpServletRequest request, HttpServletResponse response) {
        Map params = Input.asParameters(request);
        log.debug("signView, params:{}",params);
        Result signResult = verifySign(signData,request);
        String page = "site/account/signFailure";
        if (signResult.isSuccess()){
            page = "site/account/signSuccess";
        }
        String redirectLink = signData.getRedirectLink();

        ModelAndView modelAndView = new ModelAndView(page);
        modelAndView.addObject("signResult",signResult);
        modelAndView.addObject("redirectLink",redirectLink);
        return modelAndView;
    }


}
