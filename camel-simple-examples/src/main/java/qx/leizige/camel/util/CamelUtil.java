package qx.leizige.camel.util;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelUtil {


    public static CamelContext getCamelContext() {
        return new DefaultCamelContext();
    }


    public static CamelContext getCamelContext(RouteBuilder routeBuilder) throws Exception {
        DefaultCamelContext context = new DefaultCamelContext();
        context.addRoutes(routeBuilder);
        return context;
    }
}
