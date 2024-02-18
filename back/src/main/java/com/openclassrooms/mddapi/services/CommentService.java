package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.NewCommentDTO;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.payload.response.CommentResponse;
import com.openclassrooms.mddapi.repositories.CommentsRepository;
import com.openclassrooms.mddapi.repositories.PostsRepository;
import com.openclassrooms.mddapi.repositories.UsersRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentsRepository commentsRepository;

    private final UsersRepository userRepository;

    private final PostsRepository postsRepository;

    public CommentService(CommentsRepository commentsRepository, UsersRepository userRepository, PostsRepository postsRepository) {
        this.commentsRepository = commentsRepository;
        this.userRepository = userRepository;
        this.postsRepository = postsRepository;
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentsRepository.findAllByPostId(postId);
    }

    public ResponseEntity<CommentResponse> createComment(NewCommentDTO newCommentDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalEmail = authentication.getName();

        User user = userRepository.findByEmail(currentPrincipalEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + currentPrincipalEmail));

        Optional<Post> optionalPost = postsRepository.findById(newCommentDTO.getPostId());
        if (!optionalPost.isPresent()) {
            CommentResponse commentResponse = new CommentResponse("Post not found with id: " + newCommentDTO.getPostId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commentResponse);
        }

        Comment comment = new Comment();
        comment.setPost(optionalPost.get());
        comment.setUser(user);
        comment.setComment(newCommentDTO.getComment());
        Comment savedComment = commentsRepository.save(comment);

        if (savedComment != null) {
            CommentResponse commentResponse = new CommentResponse("Comment added with success");
            return ResponseEntity.status(HttpStatus.CREATED).body(commentResponse);
        } else {
            CommentResponse commentResponse = new CommentResponse("Failed to add comment");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(commentResponse);
        }
    }
}