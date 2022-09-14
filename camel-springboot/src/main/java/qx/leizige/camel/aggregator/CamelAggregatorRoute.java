package qx.leizige.camel.aggregator;

import com.alibaba.fastjson.JSONObject;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Component
public class CamelAggregatorRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {


        from("netty-http:http://localhost:8314/basic/actuator/info")
                .to("netty-http:http://localhost:8888/aggregator/test")
                .to("netty-http:http://localhost:8888/aggregator/test1")
                .to("netty-http:http://localhost:8888/aggregator/test2")
                .aggregate(simple("${body}"), new MyAggregationStrategy())
                .completionSize(3)
                .process(exchange -> {
                    System.out.println(exchange.getIn().getBody());
                });

    }


    @RestController
    @RequestMapping(value = "/aggregator")
    public static class AggregatorTestHandler {
        @PostMapping(value = "/test")
        public String test(@RequestBody String json) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", 1);
            return jsonObject.toJSONString();
        }

        @PostMapping(value = "/test1")
        public String test1(@RequestBody String json) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", "张三");
            return jsonObject.toJSONString();
        }

        @PostMapping(value = "/test2")
        public String test2(@RequestBody String json) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("address", "上海");
            return jsonObject.toJSONString();
        }
    }
}
