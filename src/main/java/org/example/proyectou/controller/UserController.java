package org.example.proyectou.controller;

import org.example.proyectou.model.User;
import org.example.proyectou.service.FileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final FileService fileService;

    public UserController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = fileService.readUsersFromFile();
            return ResponseEntity.ok(users);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        try {
            List<User> users = fileService.readUsersFromFile();
            Optional<User> user = users.stream().filter(u -> u.getId().equals(id)).findFirst();
            if (user.isPresent()) {
                return ResponseEntity.ok(user.get());
            } else {
                return ResponseEntity.status(404).build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
            List<User> users = fileService.readUsersFromFile();
            user.setId(UUID.randomUUID().toString());
            users.add(user);
            fileService.writeUsersToFile(users);
            return ResponseEntity.status(201).body(user);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestBody User user) {
        try {
            List<User> users = fileService.readUsersFromFile();
            Optional<User> matchingUser = users.stream()
                    .filter(u -> u.getEmail().equals(user.getEmail()) && u.getPassword().equals(user.getPassword()))
                    .findFirst();
            if (matchingUser.isPresent()) {
                return ResponseEntity.ok(matchingUser.get());
            } else {
                return ResponseEntity.status(401).body("Email o contraseña incorrectos");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        try {
            List<User> users = fileService.readUsersFromFile();
            Optional<User> existingUser = users.stream().filter(u -> u.getId().equals(id)).findFirst();
            if (existingUser.isPresent()) {
                User updatedUser = existingUser.get();
                updatedUser.setName(user.getName());
                updatedUser.setEmail(user.getEmail());
                if (user.getPassword() != null) {
                    updatedUser.setPassword(user.getPassword());
                }
                fileService.writeUsersToFile(users);
                return ResponseEntity.ok(updatedUser);
            } else {
                return ResponseEntity.status(404).build();
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        try {
            List<User> users = fileService.readUsersFromFile();
            if (users.removeIf(user -> user.getId().equals(id))) {
                fileService.writeUsersToFile(users);
                return ResponseEntity.status(204).build();
            } else {
                return ResponseEntity.status(404).build();
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }
}