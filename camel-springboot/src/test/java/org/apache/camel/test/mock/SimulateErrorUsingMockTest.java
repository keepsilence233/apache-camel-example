package org.apache.camel.test.mock;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.BaseCamelTest;
import org.junit.Test;

import java.io.IOException;
import java.net.ConnectException;

/**
 * 使用mock模拟错误
 */
public class SimulateErrorUsingMockTest extends BaseCamelTest {


    @Override
    protected RoutesBuilder createRouteBuilder() throws Exception {
        return new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                context.setTracing(true);

                errorHandler(defaultErrorHandler()
                        .maximumRedeliveries(5).redeliveryDelay(1000)
                        .retryAttemptedLogLevel(LoggingLevel.WARN));

                onException(IOException.class).maximumRedeliveries(3)
                        .handled(true)
                        .process(exchange -> {
                            log.error("exception message : {},body : {}",
                                    exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class).getMessage(), exchange.getIn().getBody(String.class));
                        })
                        .to("mock:ftp");

                from("direct:file")
                        .to("mock:http");
            }
        };
    }

    @Test
    public void testSimulateErrorUsingMock() throws Exception {
        getMockEndpoint("mock:ftp").expectedBodiesReceived("Camel rocks");

        MockEndpoint http = getMockEndpoint("mock:http");
        http.whenAnyExchangeReceived(new Processor() {
            public void process(Exchange exchange) throws Exception {
                exchange.setException(new ConnectException("Simulated connection error"));
            }
        });

        template.sendBody("direct:file", "Camel rocks");

        assertMockEndpointsSatisfied();
    }
}
