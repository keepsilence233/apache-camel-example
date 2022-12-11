package qx.leizige.camel.components.dynamic.bean;


import org.apache.camel.Exchange;
import org.apache.camel.Header;

/**
 * 实现消息应由动态路由器 EIP 路由的逻辑的 Bean。
 */
public class DynamicRouterBean {

    /**
     * 动态路由器 EIP 调用的方法来计算下一步去哪里。
     *
     * @param body     the message body
     * @param previous the previous endpoint, is <tt>null</tt> on the first invocation
     * @return endpoint uri where to go, or <tt>null</tt> to indicate no more
     */
    public String route(String body, @Header(Exchange.SLIP_ENDPOINT) String previous) {
        return whereToGo(body, previous);
    }


    /**
     * 计算下一步去哪里的方法
     */
    private String whereToGo(String body, String previous) {
        if (previous == null) {
            // 1st time
            return "mock://a";
//            return "netty-http:http://www.nmc.cn/rest/province";
        } else if ("mock://a".equals(previous)) {
            // 2nd time - transform the message body using the simple language
            return "language://simple:Bye ${body}";
        } else {
            // no more, so return null to indicate end of dynamic router
            return null;
        }
    }


}
