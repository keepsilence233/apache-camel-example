


import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ${className} implements Processor {
@Override
public void process(Exchange exchange) throws Exception {
        Logger log = LoggerFactory.getLogger(this.getClass());
        log.info("execute {} processor", this.getClass().getSimpleName());

        String body = exchange.getMessage().getBody(String.class);

        log.info("execute {} body :{}", this.getClass().getSimpleName(), body);

        exchange.getMessage().setBody(this.getClass().getSimpleName() + "-" + 666);
        }
        }
