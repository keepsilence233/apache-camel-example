### 《Camel》中开箱即用的三种拦截器  
intercept : 拦截消息的每一步。当消息被路由时，将连续调用此拦截器  
interceptFromEndpoint : 拦截到达特定端点的传入消息。此拦截器仅调用一次  
interceptSendToEndpoint：拦截将要发送到特定端点的消息。此拦截器仅调用一次
