package org.ixkit.anvilj.launcher;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="health")
@RestController
@RequestMapping("/api/v1/health")
@Slf4j
public class HealthController {

    @ApiOperation(value="Health check", notes="Health check")
    @GetMapping(value = "/check")
    public Object check(){
        return Result.OK();
    }
}
