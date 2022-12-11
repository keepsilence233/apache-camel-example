package qx.leizige.camel.components.bean;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;

/**
 * @author krzyczak
 */
@Slf4j
public class Utils {
//    public void readCsv(Exchange exchange) throws ClassNotFoundException {
//        GenericFile file = (GenericFile) exchange.getIn().getBody();
//        System.out.println("DATA: ---" + file.split("\n"));
//    }

    public void standardizeCase(Exchange exchange) throws ClassNotFoundException {
        String body = exchange.getIn().getBody(String.class);
        log.info("standardizeCase body : {}" ,JSON.toJSONString(body, true));
        exchange.getIn().setBody("set body 123123.....");
    }
}