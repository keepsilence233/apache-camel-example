package qx.leizige.camel.routes.components.convert;

import org.apache.camel.builder.RouteBuilder;


//@Component
public class TypeConvertRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("netty-http:localhost:9090/test/convert")
                .setBody(simple("666"))
                .convertBodyTo(Order.class)
                .to("log:${body}");
    }
}
