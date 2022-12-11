package qx.leizige.camel.components.jdbc.jdbccomponent;

import com.mchange.v2.c3p0.DriverManagerDataSource;
import org.apache.camel.CamelContext;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

/**
 * jdbc component
 */
@Component
public class JdbcTest extends RouteBuilder {

    private CamelContext camelContext;

    public JdbcTest(CamelContext camelContext) {
        this.camelContext = camelContext;
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setJdbcUrl("");
        dataSource.setDriverClass("");
        dataSource.setUser("");
        dataSource.setPassword("");
        camelContext.getRegistry().bind("myDataSource", dataSource);
    }

    @Override
    public void configure() throws Exception {
        from("netty-http:http://127.0.0.1:8998/jdbcTest")
                .setBody(simple("select * from tableName"))
                .to("jdbc:myDataSource?useHeadersAsParameters=true")
                .marshal().json(JsonLibrary.Jackson)
                .log(LoggingLevel.DEBUG, "${body}");

    }
}
