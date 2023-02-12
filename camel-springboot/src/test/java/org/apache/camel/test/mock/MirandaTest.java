package org.apache.camel.test.mock;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.BaseCamelTest;
import org.apache.camel.util.StringHelper;
import org.junit.Test;

/**
 * 使用模拟模拟真实组件
 */
public class MirandaTest extends BaseCamelTest {


    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            public void configure() throws Exception {
                from("jetty://http://localhost:9080/service/order")
                        .transform().message(m -> "ID=" + m.getHeader("id"))
                        .to("mock:miranda")
                        .transform().body(String.class, b -> StringHelper.after(b, "STATUS="));
            }
        };
    }


    @Test
    public void testMiranda() throws Exception {

        MockEndpoint mock = getMockEndpoint("mock:miranda");
        mock.expectedBodiesReceived("ID=123");
        //使用自定义处理器设置屏蔽答复
        mock.whenAnyExchangeReceived(exchange -> exchange.getIn().setBody("ID=123,STATUS=IN PROGRESS"));
//        mock.whenExchangeReceived(); 当收到第n条消息时，使用自定义处理器设置屏蔽回复

        String out = fluentTemplate.to("http://localhost:9080/service/order?id=123").request(String.class);
        assertEquals("IN PROGRESS", out);

        assertMockEndpointsSatisfied();
    }
}
