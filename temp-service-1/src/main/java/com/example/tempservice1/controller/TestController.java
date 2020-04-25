package com.example.tempservice1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    private RestTemplate restTemplate;

    @GetMapping("/hello")
    public Object test1(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello temp-service-1";
    }

    @GetMapping("/feign_hello2")
    public Object feignHello2(HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        map.put("serverPort",request.getServerPort());
        map.put("requestUrl",request.getRequestURL());
        map.put("remotePort",request.getRemotePort());
        map.put("time",System.currentTimeMillis());
        System.out.println(map);
        return map;
    }


    /**
     * 负载均衡测试接口(默认轮循机制)
     * restTemplate需要手动注入
     * @return
     */
    @GetMapping("/load_balance")
    public Object loadBalance(){
        return restTemplate.getForEntity("http://temp-service-2/hello2",String.class);
    }


}
