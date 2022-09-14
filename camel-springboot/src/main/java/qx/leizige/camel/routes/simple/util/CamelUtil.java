package qx.leizige.camel.routes.simple.util;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.SimpleRegistry;

public class CamelUtil {


    public static CamelContext getCamelContext() {
        return new DefaultCamelContext();
    }


    public static CamelContext getCamelContext(RouteBuilder routeBuilder) throws Exception {
        DefaultCamelContext context = new DefaultCamelContext();
        context.addRoutes(routeBuilder);
        return context;
    }

    public static CamelContext getCamelContext(RouteBuilder routeBuilder, SimpleRegistry simpleRegistry) throws Exception {
        DefaultCamelContext context = new DefaultCamelContext(simpleRegistry);
        context.addRoutes(routeBuilder);
        return context;
    }
}
