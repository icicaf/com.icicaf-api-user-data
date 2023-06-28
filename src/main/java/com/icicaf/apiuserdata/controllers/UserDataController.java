package com.icicaf.apiuserdata.controllers;

import com.icicaf.apiuserdata.domain.User;
import com.icicaf.apiuserdata.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserDataController {

    @Autowired
    private IUserService userService;

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getUserList());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable UUID uuid) {
        Optional<User> userResult = userService.getUserList().stream()
                .filter(user -> user.getId().equals(uuid))
                .findFirst();

        if(userResult.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(userResult);
        }
    }

    @PostMapping()
    public ResponseEntity createUser(@RequestBody User user) {
        List<User> userResult = userService.getUserList();
        userResult.add(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity deleteUser(@PathVariable UUID uuid) {

        if (userService.deleteUser(uuid)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
