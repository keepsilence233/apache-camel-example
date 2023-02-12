package org.apache.camel.test;

import org.apache.camel.Exchange;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.junit.Test;

import java.io.File;

public class FirstTest extends BaseCamelTest {

    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("file:/Users/chinese.youth/temp/A")
                        .to("file:/Users/chinese.youth/temp/B");
            }
        };
    }

    @Test
    public void testMove() throws InterruptedException {
        template.sendBodyAndHeader("file:/Users/chinese.youth/temp/A", "Hello World", Exchange.FILE_NAME, "hello.txt");
        Thread.sleep(2000);
        File target = new File("/Users/chinese.youth/temp/B/hello.txt");
        assertTrue("File not moved", target.exists());
    }
}
