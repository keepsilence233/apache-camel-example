package qx.leizige.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import qx.leizige.camel.util.CamelUtil;

public class NettyHttpDemo {

    public static void main(String[] args) throws Exception {
        //netty-http:http://0.0.0.0:8080[?options]
        CamelContext context = CamelUtil.getCamelContext(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("timer:check?period=5m")
                        .to("netty-http:http://106.14.149.149:8380/caculate/actuator/info")
                        .process((exchange -> {
                            String body = exchange.getIn().getBody(String.class);
                            System.out.println("body = " + body);
                        }))
                        .to("stream:out");
            }
        });

        context.start();
        Thread.sleep(3000);
        context.stop();

    }

}
