package qx.leizige.camel.routes.components.aggregator;

import org.apache.camel.LoggingLevel;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;

import java.util.Date;
import java.util.Random;

/**
 * EIP 模式中的聚合器允许您将多个消息组合成一条消息。
 */

//@Component
public class CamelAggregatorRoute extends RouteBuilder {
    final String CORRELATION_ID = "correlationId";

    @Override
    public void configure() throws Exception {
        Random random = new Random();

        from("timer:insurance?period=200")
                .process(exchange ->
                {
                    Message message = exchange.getMessage();
                    message.setHeader(CORRELATION_ID, random.nextInt(4));
                    message.setBody(new Date() + "");
                })
                .aggregate(header(CORRELATION_ID), new MyAggregationStrategy())
                .completionSize(5)
                .log(LoggingLevel.ERROR, "${header." + CORRELATION_ID + "} ${body}");
    }
}
