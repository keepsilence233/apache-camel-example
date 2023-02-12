package qx.leizige.camel.dataformat;

import org.apache.camel.Exchange;
import org.apache.camel.spi.DataFormat;
import org.apache.camel.spi.DataFormatName;
import org.apache.camel.support.service.ServiceSupport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 自定义Camel数据格式,必须实现{@link DataFormat}接口
 */
public class ReverseDataFormat extends ServiceSupport implements DataFormat, DataFormatName {


    @Override
    public void marshal(Exchange exchange, Object graph, OutputStream stream) throws Exception {
        byte[] bytes = exchange.getContext().getTypeConverter().mandatoryConvertTo(byte[].class, graph);
        String body = reverseBytes(bytes);
        stream.write(body.getBytes());
    }

    private String reverseBytes(byte[] data) {
        StringBuilder sb = new StringBuilder(data.length);
        for (int i = data.length - 1; i >= 0; i--) {
            char ch = (char) data[i];
            sb.append(ch);
        }
        return sb.toString();
    }

    @Override
    public Object unmarshal(Exchange exchange, InputStream stream) throws Exception {
        byte[] bytes = exchange.getContext().getTypeConverter().mandatoryConvertTo(byte[].class, stream);
        String body = reverseBytes(bytes);
        return body;
    }

    @Override
    public String getDataFormatName() {
        return "reverse";
    }
}
