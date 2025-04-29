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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理API
 * @author Daniel
 * @since 2025-04-28
 */
@RestController
@RequestMapping("/api/users")
@Tag(name = "用户管理", description = "用户的CRUD操作API")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "创建新用户", description = "创建一个新的用户记录")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "用户创建成功", 
                content = @Content(schema = @Schema(implementation = UserDto.class))),
        @ApiResponse(responseCode = "400", description = "无效的请求参数")
    })
    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
    }

    @Operation(summary = "获取所有用户", description = "返回系统中的所有用户列表")
    @ApiResponse(responseCode = "200", description = "成功获取用户列表")
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @Operation(summary = "根据ID获取用户", description = "根据用户ID查询用户信息")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "成功获取用户信息"),
        @ApiResponse(responseCode = "404", description = "用户不存在")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(
            @Parameter(description = "用户ID", required = true) 
            @PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(summary = "更新用户信息", description = "根据ID更新指定用户的信息")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "用户信息更新成功"),
        @ApiResponse(responseCode = "404", description = "要更新的用户不存在"),
        @ApiResponse(responseCode = "400", description = "无效的请求参数")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @Parameter(description = "用户ID", required = true) 
            @PathVariable(name = "id") Long id,
            @Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }

    @Operation(summary = "删除用户", description = "根据ID删除指定用户")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "用户删除成功"),
        @ApiResponse(responseCode = "404", description = "要删除的用户不存在")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(
            @Parameter(description = "用户ID", required = true) 
            @PathVariable(name = "id") Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }
    
    @Operation(summary = "检查用户是否存在", description = "根据ID检查用户是否存在")
    @ApiResponse(responseCode = "200", description = "返回用户存在状态")
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> checkUserExists(
            @Parameter(description = "用户ID", required = true) 
            @PathVariable(name = "id") Long id) {
        boolean exists = userService.existsById(id);
        return ResponseEntity.ok(exists);
    }
}