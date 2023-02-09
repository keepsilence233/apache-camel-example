package qx.leizige.camel.components.autotask.quartz;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * apache amel quartz component
 * {@link <a href="https://camel.apache.org/components/3.20.x/scheduler-component.html">...</a>}
 */
@Slf4j
//@Component
public class QuartzComponent extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        /* 五秒执行一次 */
        from("quartz://myTimerName?cron=*/5 * * * * ?")
                .doTry()
                    .to("netty-http:http://t.weather.sojson.com/api/weather/city/101210101")
                    .process(exchange -> log.info("call weather api response : {}", exchange.getIn().getBody(String.class)))
                    .process(exchange -> log.info("call weather api response : {}", exchange.getIn().getBody(String.class)))
                    .to("netty-http:http://localhost:8888/verify/verifyQuartzComponent")
                .doCatch(Exception.class)
                    .process(exchange -> log.info("doCatch exceptionMessage : {}", exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class).getMessage()))
                .doFinally()
                    .process(exchange -> log.info("doFinally {}", exchange.getIn().getBody(String.class)))
                .endRest();
    }
}
