package qx.leizige.camel.routes.components.trycache;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class TryCacheRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("netty-http:http://localhost:6666/foo2")
                .doTry()
                .process(exchange -> {
                    String body = exchange.getIn().getBody(String.class);
                    System.out.println("doTry body = " + body);
                    int a = 10 / 0;
                })
                .doCatch(Exception.class)
                .process(exchange -> {
                    final Throwable ex = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);
                    System.out.println("doCatch exceptionMessage = " + ex.getMessage());
                })
                .doFinally()
                .process(exchange -> {
                    System.out.println("doFinally" + 666666);
                })
                .endRest();

    }
}
