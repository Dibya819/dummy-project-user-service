package com.Practice.UserService.controller;

import com.Practice.UserService.dtos.UserRequest;
import com.Practice.UserService.dtos.UserResponse;
import com.Practice.UserService.model.User;
import com.Practice.UserService.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/createUser")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@Valid @RequestBody UserRequest userRequest) {
        userService.createUser(userRequest);
    }

    @GetMapping("/getAllUser")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUser() {
        return userService.getAllUser();
    }
    @GetMapping("/getUserById/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserById(@PathVariable long id){
        return userService.getUserById(id);
    }
    @PutMapping("/updateUser/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse updateUser(@PathVariable long id,@Valid @RequestBody UserRequest userRequest){
        return userService.updateUser(id, userRequest);
    }
    @DeleteMapping("/deleteUser/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable long id){
        userService.deleteUser(id);
    }
}

