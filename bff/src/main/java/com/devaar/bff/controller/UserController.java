package com.devaar.bff.controller;

import com.devaar.bff.model.dto.UserDto;
import com.devaar.bff.service.BffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final BffService bffService;

     @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = bffService.getAllUsers();
        return ResponseEntity.ok(users);
    }

     @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = bffService.getUserById(id);
        return ResponseEntity.ok(user);
    }

     @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
        UserDto createdUser = bffService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

     @PutMapping("/{id}")
    public ResponseEntity<Void> updateUserById(
            @PathVariable Long id, @Valid @RequestBody UserDto userDto) {
        bffService.updateUserById(id, userDto);
        return ResponseEntity.ok().build();
    }

     @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long id) {
        bffService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
