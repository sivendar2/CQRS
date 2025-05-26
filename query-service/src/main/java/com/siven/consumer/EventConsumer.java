package com.siven.consumer;

import com.siven.event.UserCreatedEvent;
import com.siven.model.UserReadModel;
import com.siven.repository.UserReadRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EventConsumer {

    private final UserReadRepository repo;

    public EventConsumer(UserReadRepository repo) {
        this.repo = repo;
    }

    @KafkaListener(topics = "audit-log-topic", groupId = "read-service")
    public void handleUserCreated(UserCreatedEvent event) {
        // Optional: add logging here
        System.out.println("Received UserCreatedEvent: " + event);

        UserReadModel user = new UserReadModel();
        user.setUserId(event.getUserId());
        user.setName(event.getName());
        user.setEmail(event.getEmail());

        repo.save(user);
    }
}
