package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.NewPostDTO;
import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.repositories.PostsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostsRepository postsRepository;

    public PostService(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
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