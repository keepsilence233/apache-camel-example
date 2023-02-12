package org.apache.camel.test;

import org.apache.camel.Exchange;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.junit.Test;

import java.io.File;

/**
 * 单元测试来测试将文件从一个目录复制到另一个目录的Camel路由
 */
public class FirstTest extends BaseCamelTest {

    /**
     * 确保起始目录为空
     */
    @Override
    public void setUp() throws Exception {
        deleteDirectory("/Users/chinese.youth/temp/A");
        deleteDirectory("/Users/chinese.youth/temp/B");
        super.setUp();
    }

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
    public void testMoveFile() throws InterruptedException {
        template.sendBodyAndHeader("file:/Users/chinese.youth/temp/A", "Hello World", Exchange.FILE_NAME, "hello.txt");

        Thread.sleep(2000);

        File target = new File("/Users/chinese.youth/temp/B/hello.txt");
        assertTrue("File not moved", target.exists());

        String content = context.getTypeConverter().convertTo(String.class, target);
        assertEquals("Hello World", content);
    }
}
