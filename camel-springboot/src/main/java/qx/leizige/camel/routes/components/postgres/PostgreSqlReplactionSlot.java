package qx.leizige.camel.routes.components.postgres;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class PostgreSqlReplactionSlot extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("pg-replication-slot://192.168.2.103:5401/finance/sync_slot:test_decoding?user={{username}}&password={{password}}&slotOptions.skip-empty-xacts=true&slotOptions.include-xids=false")
                .to("mock:result");

    }
}
