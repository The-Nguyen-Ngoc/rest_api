package com.rest_api.lear.model.response;

import lombok.Data;

@Data
public class UserDetailResponse {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
}
