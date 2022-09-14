package qx.leizige.camel.routes.components.quartz;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class CamelQuartzExample extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        /* 五秒执行一次 */
        from("quartz://myTimerName?cron=*/5 * * * * ?")
                .to("netty-http:http://localhost:8301/stargate/fieldConvertPluginConfig")
                .log("${body}")
                .setBody(simple("{\"name\":\"zs\"}"))
                .to("netty-http:http://localhost:8301/stargate/routeScene");

    }

}
