package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdatedUserDTO {


    @NonNull
    @Size(max = 50)
    @Email
    private String email;


    @NonNull
    @Size(max = 50)
    private String name;
}
