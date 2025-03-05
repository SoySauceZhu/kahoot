package mingjie.kahoot.userservice.controller;
import mingjie.kahoot.userservice.entity.User;
import mingjie.kahoot.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户接口
 * 路径前缀 /users
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 查询所有用户
     * GET /users
     */
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * 根据ID查询用户
     * GET /users/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    /**
     * 新增用户
     * POST /users
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        // 示例：先检查用户名是否已存在
        if (userService.getUserByUsername(newUser.getUsername()) != null) {
            // 冲突：用户名已被占用
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        User savedUser = userService.createUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    /**
     * 更新用户
     * PUT /users/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,
                                           @RequestBody User updateData) {
        // 先根据ID查数据库是否存在
        User dbUser = userService.getUserById(id);
        if (dbUser == null) {
            return ResponseEntity.notFound().build();
        }
        // 如果只更新部分字段，可以这里做字段合并
        dbUser.setUsername(updateData.getUsername());
        dbUser.setPassword(updateData.getPassword());
        dbUser.setEmail(updateData.getEmail());
        dbUser.setRole(updateData.getRole());

        userService.updateUser(dbUser);
        return ResponseEntity.ok(dbUser);
    }

    /**
     * 删除用户
     * DELETE /users/{id}
     */
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