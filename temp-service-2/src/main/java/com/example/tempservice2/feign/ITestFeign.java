package com.example.tempservice2.feign;

import feign.hystrix.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lyy
 * @date 2020/4/25 7:10 下午
 */
@FeignClient(value = "temp-service-1",fallbackFactory = ITestFeign.TestFeignFactoryImpl.class)
public interface ITestFeign {

    @GetMapping("/feign_hello2")
    Object  feignHello2();


    @PostMapping("/feign_hello3")
    Object feignHello3();

    @Component
    class TestFeignFactoryImpl implements FallbackFactory<ITestFeign> {

        @Override
        public ITestFeign create(Throwable throwable) {
            return new ITestFeign() {
                @Override
                public Object feignHello2() {
                    Map<String,Object> result=new HashMap<>();
                    result.put("code",1);
                    result.put("state",false);
                    result.put("message","失败");
                    result.put("desc","feign调用服务降级熔断");
                    return result;
                }

                @Override
                public Object feignHello3() {
                    Map<String,Object> result=new HashMap<>();
                    result.put("code",1);
                    result.put("state",false);
                    result.put("message","feign调用服务降级熔断");
                    return result;
                }
            };
        }
    }
}
