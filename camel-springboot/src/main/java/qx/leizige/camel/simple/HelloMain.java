package qx.leizige.camel.simple;

import org.apache.camel.main.Main;
import qx.leizige.camel.simple.route.HelloRoute;

/**
 * Camel提供了一个org.apache.Camel.main.main类，使其更易于运行
 * 使用Main类运行Camel更容易。run方法1是一种保持JVM运行的阻塞方法。
 * Main类还确保您在JVM关闭信号时触发并执行Camel的正常关闭，以便Camel应用程序正常终止。
 * 当该示例运行时，您可以从上的web浏览器访问该服务 <a href="http://localhost:8080/hello">...</a>
 */
public class HelloMain {
    public static void main(String[] args) throws Exception {
        // use org.apache.camel.main.Main to make it easier to run Camel standalone
        Main main = new Main();
        // add the routes
        main.configure().addRoutesBuilder(new HelloRoute());
        // run the application (keep it running)
        main.run();
    }
}
