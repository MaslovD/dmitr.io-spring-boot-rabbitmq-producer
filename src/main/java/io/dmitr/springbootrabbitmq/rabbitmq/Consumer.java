package io.dmitr.springbootrabbitmq.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class Consumer {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);

    @RabbitListener(queues = "${queue-name}")
    public void handler(String message) {
        log.info("Consumer> " + message);
    }
}
