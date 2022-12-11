package qx.leizige.camel.components.bean;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;

@Slf4j
//@Component
public class CamelBeanRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("file:D:\\github\\apache-camel\\camel-springboot\\src\\main\\resources\\files?fileName=order.json&noop=true")
                .log("${body}")
                .bean(Utils.class, "standardizeCase")
                .log("\n\n\n ----------- LIST ${body} ------------ \n\n\n");
    }
}
