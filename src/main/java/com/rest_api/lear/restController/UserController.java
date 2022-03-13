package com.rest_api.lear.restController;

import com.rest_api.lear.dto.UserDto;
import com.rest_api.lear.model.request.UserDetailRequest;
import com.rest_api.lear.model.response.UserDetailResponse;
import com.rest_api.lear.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public String getUsers() {
        return "Hello World";
    }

    @PostMapping("")
    public UserDetailResponse postUsers(@RequestBody UserDetailRequest userDetailRequest) {
        UserDetailResponse userDetailResponse = new UserDetailResponse();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetailRequest, userDto);

        UserDto userDto1 = userService.createUser(userDto);
        BeanUtils.copyProperties(userDto1, userDetailResponse);


        return userDetailResponse;
    }

}
