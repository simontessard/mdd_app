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

    public void delete(Long id) {
        this.usersRepository.deleteById(id);
    }

    public User findById(Long id) {
        return this.usersRepository.findById(id).orElse(null);
    }
}