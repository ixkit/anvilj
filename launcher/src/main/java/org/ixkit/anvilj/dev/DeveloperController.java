package org.ixkit.anvilj.dev;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.constant.CacheConstant;
import org.jeecg.common.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile("dev")
@Api(tags="developer")
@RestController
@RequestMapping("/api/v1/developer")
@Slf4j
public class DeveloperController {

    @Autowired
    private RedisUtil redisUtil;
    @ApiOperation(value="Redis management", notes="Redis management")
    @GetMapping(value = "/redis")
    public Object redisOpt(){

        return Result.OK();
    }
}
