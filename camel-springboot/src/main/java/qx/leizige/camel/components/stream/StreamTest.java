package qx.leizige.camel.components.stream;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

/**
 * camel stream component test
 */
public class StreamTest {

    public static void main(String[] args) throws Exception {

        Main main = new Main();
        main.configure().addRoutesBuilder(new StreamRoutesBuilder());
        main.run();
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
