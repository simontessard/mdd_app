package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.NewPostDTO;
import com.openclassrooms.mddapi.models.*;
import com.openclassrooms.mddapi.payload.response.PostResponse;
import com.openclassrooms.mddapi.repositories.PostsRepository;
import com.openclassrooms.mddapi.repositories.UsersRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostsRepository postsRepository;

    private final CommentService commentService;

    private final SubService subService;
    private final UsersRepository userRepository;

    public PostService(PostsRepository postsRepository, CommentService commentService, SubService subService, UsersRepository userRepository) {
        this.postsRepository = postsRepository;
        this.commentService = commentService;
        this.subService = subService;
        this.userRepository = userRepository;
    }

    public Optional<PostResponse> getPost(Long id) {
        Optional<Post> postOptional = postsRepository.findById(id);
        if (!postOptional.isPresent()) {
            return Optional.empty();
        }

        Post post = postOptional.get();
        List<Comment> comments = commentService.getCommentsByPostId(id);

        PostResponse postResponse = new PostResponse();
        postResponse.setId(post.getId());
        postResponse.setAuthor(post.getAuthor());
        postResponse.setTopic(post.getTopic());
        postResponse.setTitle(post.getTitle());
        postResponse.setContent(post.getContent());
        postResponse.setCreatedAt(post.getCreatedAt());
        postResponse.setComments(comments);

        return Optional.of(postResponse);
    }
    public List<Post> getAllPosts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalEmail = authentication.getName();

        User user = userRepository.findByEmail(currentPrincipalEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + currentPrincipalEmail));

        List<Sub> subscriptions = subService.getSubscriptionsByUserId(user.getId());
        List<Long> topicIds = subscriptions.stream().map(Sub::getTopicId).collect(Collectors.toList());

        return postsRepository.findAll().stream()
                .filter(post -> topicIds.contains(post.getTopic().getId()))
                .collect(Collectors.toList());
    }

    public Post createPost(NewPostDTO newPostDTO, String author) {
        Post post = new Post();
        post.setTitle(newPostDTO.getTitle());
        post.setContent(newPostDTO.getContent());

        Topic topic = new Topic();
        topic.setId(newPostDTO.getTopicId());
        post.setTopic(topic);

        post.setAuthor(author);
        return postsRepository.save(post);
    }
}