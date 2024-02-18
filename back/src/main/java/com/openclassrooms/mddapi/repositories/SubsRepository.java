package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.models.Sub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubsRepository extends JpaRepository<Sub, Long> {

    List<Sub> findByUserId(Long userId);

    boolean existsByUserIdAndTopicId(Long userId, Long topicId);

    Sub findByUserIdAndTopicId(Long userId, Long topicId);
}
