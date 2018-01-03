package io.dmitr.springbootrabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class Producer {
    private static final Logger log = LoggerFactory.getLogger(Producer.class);

    private final RabbitTemplate rabbitTemplate;
    private Integer counter = 0;

    @Autowired
    public Producer(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 580L)
    public void sendMessage() {


        final CustomMessage message = new CustomMessage("Message #".concat(counter.toString()), new Random().nextInt(50), false);
        log.info("Sending message...");

        rabbitTemplate.convertAndSend(SpringBootRabbitmqProducerApplication.EXCHANGE_NAME, SpringBootRabbitmqProducerApplication.ROUTING_KEY, message);

        counter++;
    }
}
