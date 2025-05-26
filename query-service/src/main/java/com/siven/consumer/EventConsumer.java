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
        System.out.println("Received UserCreatedEvent: " + event);

        try {
            // Check if already exists
            if (!repo.existsById(event.getUserId())) {
                UserReadModel user = new UserReadModel();
                user.setUserId(event.getUserId());
                user.setName(event.getName());
                user.setEmail(event.getEmail());
                if ("fail@example.com".equalsIgnoreCase(event.getEmail())) {
                    throw new RuntimeException("Simulated processing failure");
                }

                repo.save(user);
            } else {
                System.out.println("User already exists in read model: " + event.getUserId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @KafkaListener(topics = "main-topic.DLQ", groupId = "dlq-group")
    public void handleDLQ(UserCreatedEvent failedEvent) {
        System.out.println("DLQ Received: " + failedEvent);
        // Log, alert, or reprocess here
    }

}
