package com.openclassrooms.mddapi.payload.response;

import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.Topic;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private Long id;
    private String author;
    private Topic topic;
    private String title;
    private String content;
    private Timestamp createdAt;
    private List<Comment> comments;
}