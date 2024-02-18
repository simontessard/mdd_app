package com.openclassrooms.mddapi.payload.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentResponse {
    private String message;

    public CommentResponse(String message) {
        this.message = message;
    }

}