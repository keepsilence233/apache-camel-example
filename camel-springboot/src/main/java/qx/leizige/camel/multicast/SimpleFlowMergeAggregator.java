package qx.leizige.camel.multicast;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SimpleFlowMergeAggregator implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        log.info("Inside aggregator " + newExchange.getIn().getBody());
        if (oldExchange == null) {
            String data = newExchange.getIn().getBody(String.class);
            List<String> aggregatedDataList = new ArrayList<>();
            aggregatedDataList.add(data);
            newExchange.getIn().setBody(aggregatedDataList);
            return newExchange;
        }

        List<String> oldData = oldExchange.getIn().getBody(List.class);
        oldData.add(newExchange.getIn().getBody(String.class));
        oldExchange.getIn().setBody(oldData);

        return oldExchange;
    }
}