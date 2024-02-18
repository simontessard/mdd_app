package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubDTO {
    @NonNull
    private Long userId;

    @NonNull
    private Long topicId;

    @NonNull
    @Size(max = 100)
    private String title;
}
