package org.ixkit.anvilj.website.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.ixkit.anvilj.web.io.Input;
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
        website/view/?page=site/policy/index.html => resources/public/site/policy/index.html
     */
    @ApiOperation(value="view", notes="view")
    @RequestMapping(value = "/view",method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView webViewPage(ModelAndView modelAndView, HttpServletRequest request, HttpServletResponse response) {
        Map params = Input.asParameters(request);
        log.debug("webViewPage, params:{}",params);
        String page = (String)  params.get("page");
        modelAndView.addAllObjects(params);
        modelAndView.setViewName(page);
        return modelAndView;
    }
}
