package com.example.firstdemo.service;

import com.example.firstdemo.dto.UserDto;

import java.util.List;

public interface UserService {
    
    UserDto createUser(UserDto userDto);
    
    UserDto getUserById(Long id);
    
    List<UserDto> getAllUsers();
    
    UserDto updateUser(Long id, UserDto userDto);
    
    void deleteUser(Long id);
    
    boolean existsById(Long id);
}
