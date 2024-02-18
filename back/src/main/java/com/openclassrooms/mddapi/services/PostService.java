package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.NewPostDTO;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.payload.response.PostResponse;
import com.openclassrooms.mddapi.repositories.PostsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostsRepository postsRepository;

    private final CommentService commentService;

    public PostService(PostsRepository postsRepository, CommentService commentService) {
        this.postsRepository = postsRepository;
        this.commentService = commentService;
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
        return postsRepository.findAll();
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