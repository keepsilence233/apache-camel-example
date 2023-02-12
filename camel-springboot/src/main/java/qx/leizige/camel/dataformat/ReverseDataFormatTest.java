package qx.leizige.camel.dataformat;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.spi.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 测试自定义数据格式{@link ReverseDataFormat}
 */
//@Component
public class ReverseDataFormatTest extends RouteBuilder {

    @Autowired
    private ProducerTemplate template;

    @Override
    public void configure() throws Exception {
        String in = "Test String";
        DataFormat format = new ReverseDataFormat();
//        from("direct:in").marshal(format);
//        from("direct:back").unmarshal(format);

        from("quartz://myTimerName?cron=*/20 * * * * ?")
                .setBody(simple(in))
                .marshal(format)
                .log("in marshal:${body}")
                .unmarshal(format)
                .log("marshal unmarshal:${body}").end();

//        Object marshalled = template.requestBody("direct:in", in);
//        log.info("in marshal : {}", marshalled);
//        Object o = template.requestBody("direct:back", marshalled);
//        log.info("in marshalled : {}", o);
    }
}
