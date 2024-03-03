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

    public boolean isValidPassword(String password) {
        // Check if password length is greater than or equal to 8
        if (password.length() < 8) {
            return false;
        }

        // Check if password contains at least one digit
        if (!password.matches(".*\\d.*")) {
            return false;
        }

        // Check if password contains at least one lowercase letter
        if (!password.matches(".*[a-z].*")) {
            return false;
        }

        // Check if password contains at least one uppercase letter
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }

        // Check if password contains at least one special character
        if (!password.matches(".*[!@#$%&*()_+=|<>?{}\\[\\]~-].*")) {
            return false;
        }

        return true;
    }
}
