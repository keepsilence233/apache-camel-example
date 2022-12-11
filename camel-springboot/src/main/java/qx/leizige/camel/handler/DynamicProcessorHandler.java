package qx.leizige.camel.handler;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.camel.CamelContext;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

/**
 * camel router dynamic add processor
 */
@RequestMapping(value = "/dynamicProcessor")
@RestController
public class DynamicProcessorHandler {


    @Autowired
    private Configuration configuration;


    private final DefaultCamelContext camelContext;

    public DynamicProcessorHandler(CamelContext camelContext) {
        this.camelContext = (DefaultCamelContext) camelContext;
    }


    @GetMapping(value = "/dynamicLoadRoute")
    public void dynamicLoadRoute() throws Exception {
        Map<String, String> dataModel = new HashMap<>();
        dataModel.put("className", "MyProcessor");
        //加载模板
        Template template = configuration.getTemplate("${className}.java");

        PrintWriter writer = new PrintWriter("/Users/chinese.youth/temp1/" + "MyProcessor.java");
        template.process(dataModel, writer);

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, "/Users/chinese.youth/temp1/" + "MyProcessor.java");

        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        URL classes = new URL("file:///Users/chinese.youth/temp1/");
        ClassLoader custom = new URLClassLoader(new URL[]{classes}, systemClassLoader);

        // this class should be loaded from your directory
        Class<?> clazz = custom.loadClass("MyProcessor");
        Processor myProcessor = (Processor) clazz.newInstance();


        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("netty-http:http:localhost:9999")
                        .process(myProcessor)
                        .end();
            }
        });


    }

}
