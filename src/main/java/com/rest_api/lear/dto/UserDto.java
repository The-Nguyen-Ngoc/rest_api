package com.rest_api.lear.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    public static final long serialVersionUID = 1L;
    private Long id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;
    private String emailVerificationToken;
    private Boolean emailVerificationStatus = false;
}
