package mingjie.kahoot.userservice.dao;

import mingjie.kahoot.userservice.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 *
 */
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM users WHERE id = #{id}")
    User findById(Long id);

    @Select("SELECT * FROM users")
    List<User> findAll();

    @Insert("INSERT INTO users(username, password, email, role, created_at, updated_at, enabled) " +
            "VALUES(#{username}, #{password}, #{email}, #{role}, #{createdAt}, #{updatedAt}, #{enabled})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(User user);

    @Update("UPDATE users SET username = #{username}, password = #{password}, email = #{email}, " +
            "role = #{role}, created_at = #{createdAt}, updated_at = #{updatedAt}, enabled = #{enabled} " +
            "WHERE id = #{id}")
    void updateUser(User user);

    @Delete("DELETE FROM users WHERE id = #{id}")
    void deleteUser(Long id);

    @Select("SELECT * FROM users WHERE username = #{username}")
    User findByUsername(String username);

    @Select("SELECT * FROM users WHERE email = #{email}")
    User findByEmail(String email);

}
