package mingjie.kahoot.userservice.controller;

import mingjie.kahoot.userservice.entity.User;
import mingjie.kahoot.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        if (!newUser.isEnabled()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        if (userService.getUserByUsername(newUser.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        User savedUser = userService.createUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,
                                           @RequestBody User updateData) {
        User dbUser = userService.getUserById(id);
        if (dbUser == null) {
            return ResponseEntity.notFound().build();
        }
        dbUser.setUsername(updateData.getUsername());
        dbUser.setPassword(updateData.getPassword());
        dbUser.setEmail(updateData.getEmail());
        dbUser.setRole(updateData.getRole());

        userService.updateUser(dbUser);
        return ResponseEntity.ok(dbUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User dbUser = userService.getUserById(id);
        if (dbUser == null) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}