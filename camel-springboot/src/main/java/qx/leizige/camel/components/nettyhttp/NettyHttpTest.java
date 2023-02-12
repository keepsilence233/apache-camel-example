package qx.leizige.camel.components.nettyhttp;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

public class NettyHttpTest {


    public static void main(String[] args) throws Exception {

        Main main = new Main();
        main.configure().addRoutesBuilder(new NettyHttpRouteBuilder());
        main.run();
    }

    static class NettyHttpRouteBuilder extends RouteBuilder {
        String json = "{\"key\":\"123123\"}";

        @Override
        public void configure() throws Exception {
            from("netty-http:http://localhost:9890/test")
                    .setBody(simple(json, String.class))
                    .process(exchange -> {
                        System.out.println(exchange.getIn().getBody());
                    })
                    .filter().method(MyBean.class, "isGoldCustomer")
                    .to("netty-http:http://101.132.24.133:8803/middle-platform-adapter/actuator")
                    .process((exchange -> {
                        String body = exchange.getIn().getBody(String.class);
                        System.out.println("body = " + body);
                    }))
                    .to("stream:out");
        }
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
