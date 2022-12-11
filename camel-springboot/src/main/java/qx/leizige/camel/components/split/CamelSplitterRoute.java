package qx.leizige.camel.components.split;


import lombok.Builder;
import lombok.Data;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.List;

//@Component
public class CamelSplitterRoute extends RouteBuilder implements ApplicationRunner {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Override
    public void configure() throws Exception {

        from("direct:start")
                .log("Before Split line ${body}")
                //指定分隔符为# 默认逗号
                .split(body()).delimiter("#")
                .log("Split line ${body}")
                .to("mock:split");

        //Complex Split
        from("direct:customerOrder")
                .log("Customer Id: ${body.customerId}")
                .split(simple("${body.orders}"))
                .log("Order: ${body}");

        //Split and Aggregate
        from("direct:customerOrderAggregate")
                .split(body(), new WordAggregationStrategy()).stopOnException()
                .bean(WordTranslateBean.class).to("mock:split")
                .end()
                .log("Aggregated ${body}")
                .to("mock:aggregatedResult");

    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
//        producerTemplate.sendBody("direct:start", "A#B#C");
//        // Order
//        List<Order> orders = new ArrayList<>();
//        orders.add(Order.builder().orderId("O100").itemIds(Arrays.asList("I100", "I101", "I102")).build());
//        orders.add(Order.builder().orderId("O200").itemIds(Arrays.asList("I200", "I201", "I202")).build());
//        CustomerOrders customerOrders = CustomerOrders.builder().customerId("Saggu").orders(orders).build();
//        producerTemplate.sendBody("direct:customerOrder", customerOrders);

        producerTemplate.sendBody("direct:customerOrderAggregate", "A,B,C");

    }



    @Data
    @Builder
    static class CustomerOrders  {
        private String customerId;
        private List<Order> orders;
    }

    @Data
    @Builder
    static class Order {
        private String orderId;
        private List<String> itemIds;
    }
}
