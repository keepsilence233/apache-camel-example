package qx.leizige.camel.routes.simple;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import qx.leizige.camel.routes.simple.util.CamelUtil;

public class StreamDemo {

    public static void main(String[] args) throws Exception {

        CamelContext context = CamelUtil.getCamelContext(new StreamRoutesBuilder());

        context.start();
        Thread.sleep(3000);
        context.stop();
    }


    static class StreamRoutesBuilder extends RouteBuilder {

        @Override
        public void configure() {
            from("stream:file?fileName=D://2.txt")
                    .to("stream:out")
                    .to("stream:file?fileName=D://3.txt")
                    .process(exchange -> {
                        String body = exchange.getIn().getBody(String.class);
                        System.out.println("getIn" + " ===  " + body);
                        exchange.getIn().setBody("change Body");
                    }).to("stream:out");
        }
    }
}
