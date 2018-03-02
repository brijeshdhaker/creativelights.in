package com.org.onclick.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.org.onclick.domain.Order;
import java.util.concurrent.CountDownLatch;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    private static Logger log = LoggerFactory.getLogger(MessageListener.class);
    private CountDownLatch latch = new CountDownLatch(10);

    public void receiveMessage(Order order) {
        log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
        log.info("######          Message Details           #####");
        log.info("- - - - - - - - - - - - - - - - - - - - - - - -");
        log.info("received <" + order.toString() + ">");
    }
    
}
