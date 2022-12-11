package qx.leizige.camel.components.memorymessage.directvm;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.Map;

/**
 * 当生产者发送消息交换时，Direct-Vm 组件提供对 JVM 中任何消费者的直接、同步调用。
 * 此端点可用于连接同一 camel 上下文中的现有路由，以及来自同一JVM中的其他 camel 上下文的现有路由
 */
@Slf4j
//@Component
//@Order(Integer.MAX_VALUE)
public class DirectVmTest extends RouteBuilder implements CommandLineRunner {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Override
    public void run(String... args) throws Exception {

        Map<String, Object> headers = Maps.newHashMap();
        headers.put("dynamicUri", "http://www.nmc.cn/rest/province");
        String o = producerTemplate.requestBodyAndHeaders("direct-vm://MyRoute", "no-body", headers, String.class);
        log.info("direct-vm://MyRoute result : {}", o);
    }

    @Override
    public void configure() throws Exception {
        from("direct-vm:MyRoute")
                .toD("${header.dynamicUri}");

    }
}
