package qx.leizige.camel.routes.components.convert;

import org.apache.camel.Converter;
import org.apache.camel.Exchange;
import org.springframework.util.StringUtils;

@Converter
public final class OrderConverter {

    @Converter
    public static Order toOrder(String data, Exchange exchange) {
        if (!StringUtils.isEmpty(data)) {
            return new Order("convertOrderName", "convertOrderNo");
        }
        return new Order("nullOrderName", "nullOrderNo");
    }


}
