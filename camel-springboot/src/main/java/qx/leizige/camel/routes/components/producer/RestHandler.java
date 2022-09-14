package qx.leizige.camel.routes.components.producer;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.FluentProducerTemplate;
import org.apache.camel.Message;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/producer")
public class RestHandler {

    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    private ConsumerTemplate consumerTemplate;

    @Autowired
    private FluentProducerTemplate fluentProducerTemplate;

    @GetMapping(value = "/t")
    public void t() {
        consumerTemplate.receiveBody("http://localhost:8888/producer/test1");

        String result1 = consumerTemplate.receiveBody("http://localhost:8888/producer/test2", String.class);
        System.out.println(result1);

        String result2 = consumerTemplate.receiveBody("http://localhost:8888/producer/test3", String.class);
        System.out.println(result2);


        String result3 = producerTemplate.requestBody(getCXFEndpointUri(SERVICE_ADDRESS, WSDL_URL), value, String.class);
        System.out.println(result3);
    }

    @GetMapping(value = "/test1")
    public void test1() {
        System.out.println("test1 ......");
    }

    @GetMapping(value = "/test2")
    public ResponseEntity<String> test2() {
        System.out.println("test2 ......");
        return ResponseEntity.ok("test2......");
    }


    @GetMapping(value = "/test3")
    public ResponseEntity<List<Integer>> test3() {
        System.out.println("test3 ......");
        return ResponseEntity.ok(Lists.newArrayList(1, 2, 3, 4, 5, 6));
    }


    public static final String SERVICE_ADDRESS = "http://localhost:8080/webservice/weaverOA";
    public static final String WSDL_URL = "http://localhost:8080/webservice/weaverOA?wsdl";

    public String getCXFEndpointUri(String serviceAddress, String wsdlURL) {
//        "cxf:"
//                + "http://localhost:8080/webservice/weaverOA" //service address
//                + "?"
//                + "wsdlURL=http://localhost:8080/webservice/weaverOA?wsdl"    //wsdl url
//                + "&"
//                + "dataFormat=RAW"        //dataformat type
        return new StringBuilder("cxf:")
                .append(serviceAddress).append("?wsdlURL=")
                .append(wsdlURL).append("&").append("dataFormat=RAW").toString();
    }

    String value = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:impl=\"http://impl.api.webservice.leizige.qx/\">\n" +
            "   <soapenv:Header/>\n" +
            "   <soapenv:Body>\n" +
            "      <impl:auditNotPassed>\n" +
            "         <!--Optional:-->\n" +
            "         <impl:requestId>88888888</impl:requestId>\n" +
            "      </impl:auditNotPassed>\n" +
            "   </soapenv:Body>\n" +
            "</soapenv:Envelope>";
}
