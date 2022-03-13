package com.rest_api.lear.model.request;

import lombok.Data;

@Data
public class UserDetailRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
