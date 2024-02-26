package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repositories.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User findById(Long id) {
        return this.usersRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        return this.usersRepository.save(user);
    }
}
