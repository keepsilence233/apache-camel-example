### 自定义camel数据格式
数据格式是可插拔的转换器，可以将消息从一种形式转换为另一种形式，反之亦然。每种数据格式在Camel中表示为org.space.Camel.spi.DataFormat中的接口，包含两种方法：  
marshal :用于将消息封送为另一种形式，例如将Java对象封送为XML、CSV、EDI、HL7、JSON或其他众所周知的数据模型  
unmarshal : 用于执行反向操作，将已知格式的数据转换回消息
