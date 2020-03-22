package com.xjuzi.tms;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class TmsApplicationTests {

    @Test
    void contextLoads() {
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.info("1111");
        logger.trace("trace");
        logger.debug("info debug");
        logger.warn("warn");
    }

}
