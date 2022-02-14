package qx.leizige.camel.bean;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.support.SimpleRegistry;
import qx.leizige.camel.util.CamelUtil;

public class BeanDemo {

    public static void main(String[] args) throws Exception {

        SimpleRegistry simpleRegistry = new SimpleRegistry(); // this class is just a mapper
        simpleRegistry.bind("ExampleBean", ExampleBean.class, new ExampleBean());


        CamelContext context = CamelUtil.getCamelContext(new RouteBuilder() {
            @Override
            public void configure() throws Exception {

                from("direct:start")
                        .to("bean:ExampleBean?method=exampleMethod").to("direct:b");

                from("direct:b")
                        .process(exchange -> {
                            String body = exchange.getIn().getBody(String.class);
                            System.out.println("body = " + body);
                        });
            }
        });

        context.start();
        Thread.sleep(1000);
        context.stop();
    }

}
