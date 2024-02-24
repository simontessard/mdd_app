package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.payload.response.CommentResponse;
import com.openclassrooms.mddapi.payload.response.CommentsResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.mddapi.services.CommentService;
import com.openclassrooms.mddapi.dto.NewCommentDTO;

import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/api/comments")
    public ResponseEntity<CommentResponse> createMessage(@Valid @RequestBody NewCommentDTO newComment) {
        return commentService.createComment(newComment);
    }

    @GetMapping("/api/comments/{postId}")
    public ResponseEntity<List<CommentsResponse>> getCommentsByPostId(@PathVariable Long postId) {
        List<Comment> comments = commentService.getCommentsByPostId(postId);
        List<CommentsResponse> commentsResponses = comments.stream()
                .map(this::convertToCommentsResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(commentsResponses);
    }

    private CommentsResponse convertToCommentsResponse(Comment comment) {
        CommentsResponse commentsResponse = new CommentsResponse();
        commentsResponse.setAuthor(comment.getUser().getEmail());
        commentsResponse.setComment(comment.getComment());
        commentsResponse.setCreatedAt(comment.getCreatedAt());
        return commentsResponse;
    }
}
