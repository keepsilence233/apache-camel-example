package qx.leizige.camel;

import freemarker.template.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import static freemarker.template.Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS;

/**
 * @author leizige camel integrated springboot
 * {@link ImportResource @ImportResource} 将 Camel XML DSl 结合 SpringBoot 使用
 */
@SpringBootApplication
//@ImportResource("classpath:camel-spring-xml/mycamel.xml")
public class SpringCamelApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCamelApplication.class, args);
    }

    @Bean
    public Configuration wordConfiguration() {
        Configuration result = new Configuration(DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        result.setDefaultEncoding("utf-8");
        //设置模板加载器
        result.setClassForTemplateLoading(this.getClass(), "/template");
        return result;
    }
}
