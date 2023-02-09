package qx.leizige.camel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @ImportResource 将 Camel XML DSl 结合 SpringBoot 使用
 */
@SpringBootApplication
//@ImportResource("classpath:camel-xml/mycamel.xml")
public class SpringCamelApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCamelApplication.class, args);
    }
}
