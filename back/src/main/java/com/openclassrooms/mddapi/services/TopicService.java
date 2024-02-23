package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.repositories.TopicsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicService {

    private final TopicsRepository topicsRepository;

    public TopicService(TopicsRepository topicsRepository) {
        this.topicsRepository = topicsRepository;
    }

    public List<Topic> getAllTopics() {
        return topicsRepository.findAll();
    }
    public Optional<Topic> getTopicById(Long id) {
        return topicsRepository.findById(id);
    }

}