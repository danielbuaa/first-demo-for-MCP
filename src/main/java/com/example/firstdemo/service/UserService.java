package com.example.firstdemo.service;

import com.example.firstdemo.dto.UserDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service interface for managing users
 * 
 * @author Daniel
 * @since 2025-04-28
 */
public interface UserService {
    
    UserDto createUser(UserDto userDto);
    
    UserDto getUserById(Long id);
    
    List<UserDto> getAllUsers();
    
    Page<UserDto> getAllUsers(Pageable pageable);
    
    UserDto updateUser(Long id, UserDto userDto);
    
    void deleteUser(Long id);
    
    boolean existsById(Long id);
}