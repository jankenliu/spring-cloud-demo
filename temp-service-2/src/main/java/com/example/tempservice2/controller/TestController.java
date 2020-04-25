package com.example.tempservice2.controller;

import com.example.tempservice2.feign.ITestFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lyy
 * @date 2020/4/18 10:30 下午
 */
@RestController
public class TestController {

    @Autowired
    private ITestFeign testFeign;

    @GetMapping("/hello")
    public Object hello(){
        return "hello temp-service-2";
    }

    /**
     * feign调用
     * @return
     */
    @GetMapping("/feign_load_balance")
    public Object feignLoadBalance(){
        return testFeign.feignHello2();
    }


    @GetMapping("/hello2")
    public Object hello2(HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        map.put("serverPort",request.getServerPort());
        map.put("requestUrl",request.getRequestURL());
        map.put("remotePort",request.getRemotePort());
        return map;
    }




}
