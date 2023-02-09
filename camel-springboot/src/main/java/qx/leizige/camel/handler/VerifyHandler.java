package qx.leizige.camel.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/verify")
public class VerifyHandler {


    @PostMapping(value = "/verifyQuartzComponent")
    public String verifyQuartzComponent(@RequestBody Map<String, Object> param) {
        log.info("param : {}", param);
        return "success";
    }

}
