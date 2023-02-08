package qx.leizige.camel.components.nettyhttp;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import org.springframework.stereotype.Component;

//@Component
public class NettyHttpRouteExample extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("quartz://myTimerName?cron=*/5 * * * * ?")
				.process(new Processor() {
					@Override
					public void process(Exchange exchange) throws Exception {
						String httpQuery = "id=1&name=123";
						exchange.getIn().setHeader(Exchange.HTTP_PATH, "1");
						exchange.getIn().setHeader(Exchange.HTTP_QUERY, httpQuery);
					}
				})
				.to("netty-http:http://localhost:8888/rest/add")
				.log(LoggingLevel.DEBUG,"${body}");
	}
}
