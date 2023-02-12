package org.apache.camel.test.mock;

import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.BaseCamelTest;
import org.junit.Test;

/**
 * unit-testing with the mock-component
 * 测试的三个步骤：设定期望值、运行测试和验证结果
 */
public class FirstMockTest extends BaseCamelTest {


    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("jms:topic:quote").to("mock:quote");
            }
        };
    }

    @Test
    public void testQueue() throws InterruptedException {
        MockEndpoint queue = getMockEndpoint("mock:queue");
        queue.expectedMessageCount(2);
        //验证是否收到正确的消息
//        queue.expectedBodiesReceived("camel rocks","Hello Camel");

        template.sendBody("jms:topic:queue", "camel rocks");
        template.sendBody("stub:jms:topic:quote", "Hello Camel");

        queue.assertIsSatisfied();
    }
}
