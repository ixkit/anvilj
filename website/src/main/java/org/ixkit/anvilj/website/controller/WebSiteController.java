package org.ixkit.anvilj.website.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.ixkit.anvilj.web.io.Input;
import org.ixkit.anvilj.website.model.Context;
import org.ixkit.land.lang.Argument;
import org.ixkit.land.lang.Strings;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/website")
@Api(tags="WebSite")
public class WebSiteController {

    /*
        http://localhost:8181/anvilj/website/view/?@page=hello&@trace=1 => resources/public/hello.html

        @page: identify template file path, eg: hello => resources/public/hello.html
        @trace: if set then trace out context variable value
     */
    @ApiOperation(value="view", notes="view")
    @RequestMapping(value = "/view",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView webViewPage( HttpServletRequest request, HttpServletResponse response) {
        Map params = Input.asParameters(request);
        log.debug("webViewPage, params:{}",params);
        String page = (String)  params.get("@page");
        ModelAndView modelAndView = new ModelAndView(page,params);
        attachContextIfNeeds(modelAndView,params,request);

        return modelAndView;
    }

    private void attachContextIfNeeds( ModelAndView modelAndView, Map params, HttpServletRequest request){
        String trace = (String)params.get("@trace");
        if (Strings.isEmpty(trace)) return;;
        Context context = new Context();
        context.putAll(params);
        modelAndView.addObject("@context",context);
    }
}
