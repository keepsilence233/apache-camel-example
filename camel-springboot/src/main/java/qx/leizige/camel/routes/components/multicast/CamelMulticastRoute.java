package qx.leizige.camel.routes.components.multicast;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 多播 EIP 允许将相同的消息路由到多个端点并以不同的方式处理它们。
 */
//@Component
public class CamelMulticastRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("netty-http:http://localhost:8314/basic/actuator/info")
                .multicast().parallelProcessing()
                .to("netty-http:http://localhost:8888/multiCast/test")
                .to("netty-http:http://localhost:8888/multiCast/test")
                .to("netty-http:http://localhost:8888/multiCast/test")
                .end();
    }


    @RestController
    @RequestMapping(value = "/multiCast")
    public static class TestHandler {
        @PostMapping(value = "/test")
        public void test(@RequestBody String json) {
            System.out.println(json);
        }
    }
}
