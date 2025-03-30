package com.arpit.taskk.controller;

import com.arpit.taskk.dto.UserDTO;
import com.arpit.taskk.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;


    @GetMapping("/get")
    public ResponseEntity<UserDTO> getUser() {
        Long userId = userService.getUserId();
        return ResponseEntity.ok(modelMapper.map(userService.getUserById(userId), UserDTO.class));
    }

}
