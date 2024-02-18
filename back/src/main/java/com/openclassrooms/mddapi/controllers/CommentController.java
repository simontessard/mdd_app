package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.payload.response.CommentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.services.CommentService;
import com.openclassrooms.mddapi.dto.NewCommentDTO;

import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/api/comments")
    public ResponseEntity<CommentResponse> createMessage(@Valid @RequestBody NewCommentDTO newComment) {
        return commentService.createComment(newComment);
    }
}
