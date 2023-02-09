package org.apache.camel.test.simple;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.test.simple.route.HelloRoute;


/**
 * Running Camel by:
 * - creating a CamelContext
 * - add route(s)
 * - start the CamelContext
 */
//当该示例运行时，您可以从上的web浏览器访问该服务 <a href="http://localhost:8080/hello">...</a>
public class HelloCamel {
    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new HelloRoute());
        context.start();

        // keep the JVM running (a bit of a hack)
        Thread.sleep(Integer.MAX_VALUE);
    }
}
