package qx.leizige.camel.components.autotask.scheduler;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * apache amel scheduler component
 * {@link <a href="https://camel.apache.org/components/3.20.x/scheduler-component.html">...</a>}
 */
//@Component
public class SchedulerComponent extends RouteBuilder {

    /**
     * delay : 延迟 2000 毫秒
     * @throws Exception
     */
    @Override
    public void configure() throws Exception {
        from("scheduler:myScheduler?delay=2000")
                .setBody()
                .simple("Current time is ${header.CamelTimerFiredTime}")
                .to("stream:out");
    }
}
