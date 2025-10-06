package com.Practice.UserService.service;

import com.Practice.UserService.Repository.UserRepo;
import com.Practice.UserService.dtos.UserRequest;
import com.Practice.UserService.dtos.UserResponse;
import com.Practice.UserService.exceptionHandling.UserNotFoundException;
import com.Practice.UserService.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepo userRepo;

    public void createUser(UserRequest userRequest) {
        User user = mapToEntity(userRequest);
        userRepo.save(user);
    }

    private User mapToEntity(UserRequest dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public List<UserResponse> getAllUser() {
       List<User> users=userRepo.findAll();
        return users.stream().map(this::mapToDto).toList();
    }
    private UserResponse mapToDto(User user) {
        return new UserResponse(user.getId(),user.getName(),user.getEmail());
    }

    public UserResponse getUserById(long id) {
        User user=userRepo.findById(id).orElseThrow(()->new UserNotFoundException(id));
        return mapToDto(user);
    }

    public UserResponse updateUser(long id, UserRequest userRequest) {
        User user=userRepo.findById(id).orElseThrow(()->new UserNotFoundException(id));
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        User updatedUser=userRepo.save(user);
        return mapToDto(updatedUser);
    }

    public void deleteUser(long id) {
        User user=userRepo.findById(id).orElseThrow(()->new UserNotFoundException(id));
        userRepo.deleteById(id);
        log.info("User deleted successfully");
     }
}
