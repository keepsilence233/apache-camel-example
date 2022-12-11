package qx.leizige.camel.components.convert;

import org.apache.camel.Converter;
import org.apache.camel.Exchange;
import org.apache.camel.TypeConverters;
import org.springframework.util.StringUtils;

@Converter(generateLoader = true)
public final class OrderConverter implements TypeConverters {

    @Converter
    public static Order toOrder(String data, Exchange exchange) {
        if (!StringUtils.isEmpty(data)) {
            return new Order("convertOrderName", "convertOrderNo");
        }
        return new Order("nullOrderName", "nullOrderNo");
    }


}
