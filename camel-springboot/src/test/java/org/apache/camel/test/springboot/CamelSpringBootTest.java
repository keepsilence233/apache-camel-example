package org.apache.camel.test.springboot;

import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import qx.leizige.camel.SpringCamelApplication;

/**
 * testing camel with springBoot
 */
@RunWith(CamelSpringBootRunner.class)
@SpringBootTest(classes = SpringCamelApplication.class)
public class CamelSpringBootTest {
}
