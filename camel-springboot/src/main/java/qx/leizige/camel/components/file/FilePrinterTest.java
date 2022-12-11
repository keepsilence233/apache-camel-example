package qx.leizige.camel.components.file;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * 使用File组件读取和写入文件
 */
//@Component
public class FilePrinterTest extends RouteBuilder {

    private final String IN_BOX_PATH = "";
    private final String OUT_BOX_PATH = "";


    @Override
    public void configure() throws Exception {
        from("file:" + IN_BOX_PATH + "?noop=true&recursive=true")
                .to("file:" + OUT_BOX_PATH);
    }
}
