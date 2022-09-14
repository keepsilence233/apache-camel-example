package qx.leizige.camel.aggregator;


import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.apache.camel.processor.aggregate.MemoryAggregationRepository;

import java.util.Objects;

public class MyAggregationStrategy extends MemoryAggregationRepository implements AggregationStrategy {
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (Objects.isNull(oldExchange)) {
            return newExchange;
        }

        String oldBody = oldExchange.getIn().getBody(String.class);
        String newBody = newExchange.getIn().getBody(String.class);

        String aggBody = oldBody + "->" + newBody;

        oldExchange.getIn().setBody(aggBody);

        return oldExchange;
    }
}
