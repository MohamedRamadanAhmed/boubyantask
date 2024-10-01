package com.boubyan.boubyantask.model.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@Builder
public class UserDTO {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String username;
    @NotBlank
    @Size(min = 8)
    private String password;
}
