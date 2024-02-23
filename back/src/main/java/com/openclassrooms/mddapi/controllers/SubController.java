package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.models.Sub;
import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.services.SubService;
import com.openclassrooms.mddapi.services.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/subs")
public class SubController {

    private final SubService subscriptionService;
    private final TopicService topicService;

    public SubController(SubService subscriptionService, TopicService topicService) {
        this.subscriptionService = subscriptionService;
        this.topicService = topicService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> subscribeUserToTopic(@RequestBody Sub sub) {
        boolean isSubExists = subscriptionService.isSubscriptionExists(sub.getUserId(), sub.getTopicId());
        if (isSubExists) {
            return ResponseEntity.ok(Collections.singletonMap("message", "Already subscribed to this topic"));
        } else {
            subscriptionService.subscribeUserToTopic(sub.getUserId(), sub.getTopicId());
            return ResponseEntity.ok(Collections.singletonMap("message", "Subscription successful"));
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Map<String, String>>> getSubscriptionsByUserId(@PathVariable Long userId) {
        List<Sub> subscriptions = subscriptionService.getSubscriptionsByUserId(userId);
        List<Map<String, String>> topics = subscriptions.stream()
                .map(sub -> {
                    Optional<Topic> topicOptional = topicService.getTopicById(sub.getTopicId());
                    if (topicOptional.isPresent()) {
                        Topic topic = topicOptional.get();
                        return Map.of(
                                "title", topic.getTitle(),
                                "content", topic.getDescription()
                        );
                    } else {
                        return Map.of(
                                "title", "Unknown topic",
                                "content", "No content"
                        );
                    }
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(topics);
    }

    @DeleteMapping("/{userId}/{topicId}")
    public ResponseEntity<Map<String, String>> unsubscribeUserFromTopic(@PathVariable Long userId, @PathVariable Long topicId) {
        subscriptionService.unsubscribeUserFromTopic(userId, topicId);
        return ResponseEntity.ok(Collections.singletonMap("message", "Unsubscription successful"));
    }
}