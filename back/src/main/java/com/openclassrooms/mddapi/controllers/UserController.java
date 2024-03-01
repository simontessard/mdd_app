package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.UpdatedUserDTO;
import com.openclassrooms.mddapi.dto.UserDTO;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.services.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Objects;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        try {
            User user = this.userService.findById(Long.valueOf(id));

            if (user == null) {
                return ResponseEntity.notFound().build();
            }
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setEmail(user.getEmail());
            userDTO.setName(user.getName());
            userDTO.setCreatedAt(user.getCreatedAt());
            userDTO.setUpdatedAt(user.getUpdatedAt());

            return ResponseEntity.ok().body(userDTO);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody UpdatedUserDTO updatedUserDTO) {
        try {
            User user = this.userService.findById(Long.valueOf(id));

            if (user == null) {
                return ResponseEntity.notFound().build();
            }

            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if(!Objects.equals(userDetails.getUsername(), user.getEmail())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            user.setEmail(updatedUserDTO.getEmail());
            user.setName(updatedUserDTO.getName());

            try {
                this.userService.save(user);
            } catch (DataIntegrityViolationException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already in use");
            }
            return ResponseEntity.ok(Collections.singletonMap("message", "Updated profile successfully"));

        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
