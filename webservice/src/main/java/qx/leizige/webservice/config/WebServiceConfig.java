package qx.leizige.webservice.config;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import qx.leizige.webservice.api.impl.WeaverOAApiController;

import javax.xml.ws.Endpoint;

@Configuration
public class WebServiceConfig {

    //默认servlet路径/*,如果覆写则按照自己定义的来
    @Bean
    public ServletRegistrationBean cxfServlet() {
        return new ServletRegistrationBean(new CXFServlet(),
                "/webservice/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    //把实现类交给spring管理
    @Autowired
    public WeaverOAApiController weaverOAApiController;


    //终端路径
    @Bean
    public Endpoint endpoint() {
        org.apache.cxf.jaxws.EndpointImpl endpoint = new EndpointImpl(springBus(), weaverOAApiController);
        endpoint.publish("/weaverOA");
        return endpoint;
    }
}
