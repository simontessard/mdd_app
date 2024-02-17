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

    public List<Sub> getSubscriptionsByUserId(Integer userId) {
        return subsRepository.findByUserId(userId);
    }

    public void subscribeUserToTopic(Integer userId, Integer topicId) {
        // Vérifiez d'abord si l'utilisateur est déjà abonné au topic
        if (!subsRepository.existsByUserIdAndTopicId(userId, topicId)) {
            // Si l'utilisateur n'est pas déjà abonné, créez un nouvel abonnement
            subsRepository.save(new Sub(topicId, userId));
        }
    }
}