package producer.rabbitmqproducer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import static producer.rabbitmqproducer.RabbitMQConfig.EXCHANGE;
import static producer.rabbitmqproducer.RabbitMQConfig.ROUTING_KEY;

@RestController
@RequiredArgsConstructor
public class MessagePublisher {

    private final RabbitTemplate rabbitTemplate;

    @PostMapping("/publish")
    public String publishMessage(@RequestBody CustomMessage customMessage) {
        customMessage.setMessageId(UUID.randomUUID().toString());
        customMessage.setMessageDate(new Date());
        rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, customMessage);
        return "Message published";
    }

}
