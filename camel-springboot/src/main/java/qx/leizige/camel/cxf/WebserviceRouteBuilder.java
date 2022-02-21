package qx.leizige.camel.cxf;

import org.apache.camel.LoggingLevel;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebserviceRouteBuilder extends RouteBuilder {

    private ProducerTemplate producerTemplate;

    @Autowired
    public void setProducerTemplate(ProducerTemplate producerTemplate) {
        this.producerTemplate = producerTemplate;
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

    @Override
    public void configure() throws Exception {
        //camel 调用webservice
        from("timer://foo?repeatCount=1")
                .setBody(constant(value))
                .to(getCXFEndpointUri(SERVICE_ADDRESS, WSDL_URL))
//                .convertBodyTo(String.class);
                .log(LoggingLevel.INFO, "${body}");

        String webserviceResult = producerTemplate.requestBody(getCXFEndpointUri(SERVICE_ADDRESS, WSDL_URL), value, String.class);
        webserviceResult = getWebserviceResult(webserviceResult);
        System.out.println("webserviceResult = " + webserviceResult);
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

    public static String getWebserviceResult(String webserviceResult) {
        return webserviceResult.substring(webserviceResult.indexOf("<return>"), webserviceResult.indexOf("</return>")).substring("<return>".length());
    }
}
