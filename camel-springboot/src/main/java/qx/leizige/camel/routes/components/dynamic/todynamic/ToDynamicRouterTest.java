package qx.leizige.camel.routes.components.dynamic.todynamic;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.camel.Endpoint;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit5.CamelTestSupport;
import org.junit.jupiter.api.Test;
import qx.leizige.camel.routes.components.dynamic.bean.DynamicRouterBean;

import java.util.Map;

public class ToDynamicRouterTest extends CamelTestSupport {

    @Test
    public void testDynamicRouter() throws Exception {
        Endpoint endpoint = context.getEndpoint("direct:start");
        template.setDefaultEndpoint(endpoint);
        template.sendBodyAndHeader("direct:start", "dynamicUri", "http://www.nmc.cn/rest/province");
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .log("header.dynamicUri=${header.dynamicUri}")
                        .toD("${header.dynamicUri}")
                        .process(exchange -> {
                            log.info("request result :" + exchange.getMessage().getBody(String.class));
                        });
            }
        };
    }
}
