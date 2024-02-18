package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.NewPostDTO;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.services.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping
    public Post createPost(@RequestBody NewPostDTO newPostDTO) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String author;
        if (principal instanceof UserDetails) {
            author = ((UserDetails)principal).getUsername();
        } else {
            author = principal.toString();
        }
        return postService.createPost(newPostDTO, author);
    }
}