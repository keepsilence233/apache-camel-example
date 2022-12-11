package qx.leizige.camel.components.netty4;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class NettyTcpTest extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("netty:tcp://localhost:8999?textline=true&sync=false").to("netty-http:http://www.nmc.cn/rest/province");
    }
}
