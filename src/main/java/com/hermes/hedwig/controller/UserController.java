package com.hermes.hedwig.controller;

import com.hermes.hedwig.model.dto.UserDTO;
import com.hermes.hedwig.model.entity.User;
import com.hermes.hedwig.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/add",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> addUser(@Valid @RequestBody UserDTO userDTO) {
        // TODO: validations
        User _user = userService.addUser(userDTO);
        return new ResponseEntity<>(_user, HttpStatus.CREATED);
    }


    @GetMapping(value = "/get/{userId}",
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@NotNull @PathVariable Long userId) {
        // TODO: validations
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PostMapping(value = "/update",
                consumes = MediaType.APPLICATION_JSON_VALUE,
                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> updateUser(@Valid @RequestBody UserDTO userDTO) {
        // TODO: validations
        return userService.updateUser(userDTO);
    }


}
