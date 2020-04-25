package com.example.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * gateWay降级、熔断控制器
 * @author lyy
 * @date 2020/4/19 6:53 下午
 */
@RestController
public class HystrixController {

    @GetMapping("gateWayDefaultBack")
    public Object gateWayDefaultBack(){
        Map<String,Object> map = new HashMap<>();
        map.put("code",1);
        map.put("state",false);
        map.put("message","服务异常");
        map.put("desc","服务降级处理");
        return map;
    }

    @GetMapping("gateWayDefaultBack2")
    public Object gateWayDefaultBack2(){
        Map<String,Object> map = new HashMap<>();
        map.put("code",1);
        map.put("state",false);
        map.put("message","服务异常");
        map.put("desc","服务降级处理2");
        return map;
    }
}
