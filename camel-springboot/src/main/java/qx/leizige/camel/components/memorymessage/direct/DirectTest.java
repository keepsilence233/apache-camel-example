package qx.leizige.camel.components.memorymessage.direct;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.main.Main;

/**
 * @author leizige
 * 2022/02/11
 */
public class DirectTest {
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.configure().addRoutesBuilder(new DirectRoute());
        main.run();
    }

    /**
     * direct是将您的路线链接在一起的最简单的方法之一。当它在from()定义中使用时，它会创建一个可以被其他 Camel 路由调用的同步端点。
     */
    static class DirectRoute extends RouteBuilder {

        @Override
        public void configure() throws Exception {
            from("file:D://inbox?noop=true")
                    .to("direct:processTheFile");

            from("direct:processTheFile")
                    .process(exchange -> {
                        String body = exchange.getIn().getBody(String.class);
                        System.out.println(body);
                    })
                    .to("direct:processTheFile2");

            from("direct:processTheFile2")
                    .to("file:D://outbox2");
        }
    }
}

