package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.models.Sub;
import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.services.SubService;
import com.openclassrooms.mddapi.services.TopicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<Void> subscribeUserToTopic(@RequestBody Sub sub) {
        subscriptionService.subscribeUserToTopic(sub.getUserId(), sub.getTopicId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<String>> getSubscriptionsByUserId(@PathVariable Integer userId) {
        List<Sub> subscriptions = subscriptionService.getSubscriptionsByUserId(userId);
        List<String> titles = subscriptions.stream()
                .map(sub -> topicService.getTopicById(sub.getTopicId())
                        .map(Topic::getTitle)
                        .orElse("Unknown topic"))
                .collect(Collectors.toList());
        return ResponseEntity.ok(titles);
    }
}