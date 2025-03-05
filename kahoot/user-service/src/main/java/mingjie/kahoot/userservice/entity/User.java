package mingjie.kahoot.userservice.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户实体，对应数据库中的 users 表
 */
@Data
public class User {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;            // STUDENT / TEACHER / ADMIN / etc.
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // ===== 构造器 =====
    public User() {
    }

    public User(Long id, String username, String password,
                String email, String role,
                LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
