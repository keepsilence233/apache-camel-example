package qx.leizige.camel.components.memorymessage.sead;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

/**
 * Camel中SEDA队列最常见的用途之一是连接路由以形成路由应用程序
 */
public class SeadRouter extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        // load file orders from src/data into the SEDA queue
        from("file:src/data?noop=true").to("seda:incomingOrders");

        // content-based router
        from("seda:incomingOrders")
                .choice()
                .when(header("CamelFileName").endsWith(".xml"))
                .to("seda:xmlOrders")
                .when(header("CamelFileName").endsWith(".csv"))
                .to("seda:csvOrders");

        from("seda:xmlOrders?multipleConsumers=true").to("jms:accounting");
        from("seda:xmlOrders?multipleConsumers=true").to("jms:production");

        // test that our route is working
        from("jms:accounting").process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                System.out.println("Accounting received order: "
                        + exchange.getIn().getHeader("CamelFileName"));
            }
        }).to("mock:accounting");
        from("jms:production").process(new Processor() {
            public void process(Exchange exchange) throws Exception {
                System.out.println("Production received order: "
                        + exchange.getIn().getHeader("CamelFileName"));
            }
        }).to("mock:production");
    }
}
