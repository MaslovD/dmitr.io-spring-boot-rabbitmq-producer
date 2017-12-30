package io.dmitr.springbootrabbitmq;

import io.dmitr.springbootrabbitmq.rabbitmq.Producer;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


import static java.lang.System.out;

import java.util.Date;

@EnableScheduling
@SpringBootApplication
public class SpringBootRabbitmqApplication {


    public static void main(String[] args) {
        SpringApplication.run(SpringBootRabbitmqApplication.class, args);

    }

    @Value("${queue-name}")
    String queue;

    @Bean
    Queue queue() {
        return new Queue(queue, false);
    }

//    @Bean
//    CommandLineRunner sender(Producer producer) {
//        return args -> {
//            producer.sendTo(queue, "Hello World");
//        };
//    }

    @Autowired
    Producer producer;

    @Scheduled(fixedDelay = 10L)
    public void sendMessages() {
        producer.sendTo(queue, "Hello “World at " + new Date());
    }


}
