package com.openclassrooms.mddapi.payload.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Getter
@Setter
public class CommentsResponse {
    @NonNull
    private String author;

    @NonNull
    @Size(max = 5000)
    private String comment;

    @CreatedDate
    @Column(name = "created_at")
    private Timestamp createdAt;
}
