package com.openclassrooms.mddapi.repositories;

import com.openclassrooms.mddapi.models.Sub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubsRepository extends JpaRepository<Sub, Integer> {

    List<Sub> findByUserId(Integer userId);

    boolean existsByUserIdAndTopicId(Integer userId, Integer topicId);
}
