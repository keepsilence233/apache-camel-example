package qx.leizige.camel.routes.simple;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import qx.leizige.camel.routes.simple.util.CamelUtil;

public class NettyHttpDemo {

    public static void main(String[] args) throws Exception {
        String json = "{\"key\":\"123123\"}";
        //netty-http:http://0.0.0.0:8080[?options]
        CamelContext context = CamelUtil.getCamelContext(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("netty-http:localhost:9890/test")
                        .setBody(simple(json,String.class))
                        .process(exchange -> {
                            System.out.println(exchange.getIn().getBody());
                        })
//                        .filter().xpath("[?(<$.key == '123123'>)]")
                        .filter().method(MyBean.class,"isGoldCustomer")
                        .to("netty-http:http://101.132.24.133:8803/middle-platform-adapter/actuator")
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

    public static class MyBean {
        public boolean isGoldCustomer(Exchange exchange) {
            String body = exchange.getIn().getBody(String.class);
            System.out.println(body);
            exchange.getMessage().setBody("{\"message\":\"success\"}");
            return false;
        }
    }
}
