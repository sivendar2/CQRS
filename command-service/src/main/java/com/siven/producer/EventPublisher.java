package com.siven.producer;

import com.siven.entity.User;
import com.siven.event.UserCreatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public EventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishUserCreated(User user) {
        UserCreatedEvent event = new UserCreatedEvent(user.getId(), user.getName(), user.getEmail());
        kafkaTemplate.send("audit-log-topic", event);
    }
}
