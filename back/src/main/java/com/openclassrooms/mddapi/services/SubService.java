package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.Sub;
import com.openclassrooms.mddapi.repositories.SubsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubService {

    private final SubsRepository subsRepository;

    public SubService(SubsRepository subscriptionRepository) {
        this.subsRepository = subscriptionRepository;
    }

    public List<Sub> getSubscriptionsByUserId(Long userId) {
        return subsRepository.findByUserId(userId);
    }

    public void subscribeUserToTopic(Long userId, Long topicId) {
        // Vérifiez d'abord si l'utilisateur est déjà abonné au topic
        if (!subsRepository.existsByUserIdAndTopicId(userId, topicId)) {
            // Si l'utilisateur n'est pas déjà abonné, créez un nouvel abonnement
            subsRepository.save(new Sub(topicId, userId));
        }
    }

    public void unsubscribeUserFromTopic(Long userId, Long topicId) {
        Sub sub = subsRepository.findByUserIdAndTopicId(userId, topicId);
        if (sub != null) {
            subsRepository.delete(sub);
        }
    }
}