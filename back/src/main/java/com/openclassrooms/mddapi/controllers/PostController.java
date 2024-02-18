package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.NewPostDTO;
import com.openclassrooms.mddapi.payload.response.PostResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.services.PostService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id) {
        Optional<PostResponse> post = postService.getPost(id);
        if (post.isPresent()) {
            return ResponseEntity.ok(post.get());
        } else {
            Map<String, String> error = Collections.singletonMap("message", "Article not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
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