package com.example.firstdemo.controller;

import com.example.firstdemo.dto.UserDto;
import com.example.firstdemo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * User Management API
 * @author Daniel
 * @since 2025-04-28
 */
@RestController
@RequestMapping("/api/users")
@Tag(name = "User Management", description = "API for CRUD operations on users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create a new user", description = "Creates a new user record")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "User created successfully", 
                content = @Content(schema = @Schema(implementation = UserDto.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters")
    })
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @Operation(summary = "Get user list", description = "Returns a paginated list of users")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved user list"),
        @ApiResponse(responseCode = "400", description = "Invalid pagination parameters")
    })
    @GetMapping
    public ResponseEntity<Page<UserDto>> getAllUsers(
            @Parameter(description = "Page number (starting from 0)", required = false) 
            @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size", required = false) 
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserDto> users = userService.getAllUsers(pageable);
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "Get user by ID", description = "Retrieves user information by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved user information"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(
            @Parameter(description = "User ID", required = true) 
            @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(summary = "Update user information", description = "Updates user information by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User information updated successfully"),
        @ApiResponse(responseCode = "404", description = "User to update not found"),
        @ApiResponse(responseCode = "400", description = "Invalid request parameters")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @Parameter(description = "User ID", required = true) 
            @PathVariable(name = "id") Long id,
            @Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    @Operation(summary = "Delete user", description = "Deletes a user by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "User deleted successfully"),
        @ApiResponse(responseCode = "404", description = "User to delete not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(
            @Parameter(description = "User ID", required = true) 
            @PathVariable(name = "id") Long id) {
        UserDto deletedUser = userService.getUserById(id);
        userService.deleteUser(id);
        return ResponseEntity.ok(deletedUser);
    }
    
    @Operation(summary = "Check if user exists", description = "Checks if a user exists by ID")
    @ApiResponse(responseCode = "200", description = "Returns user existence status")
    @GetMapping("/exists/{id}")
    public ResponseEntity<UserDto> checkUserExists(
            @Parameter(description = "User ID", required = true) 
            @PathVariable(name = "id") Long id) {
        if (userService.existsById(id)) {
            return ResponseEntity.ok(userService.getUserById(id));
        }
        return ResponseEntity.notFound().build();
    }
}
