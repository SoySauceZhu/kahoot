package mingjie.kahoot.userservice.dao;

import mingjie.kahoot.userservice.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 */
@Mapper
public interface UserMapper {

    /**
     */
    @Select("SELECT id, username, password, email, role, created_at, updated_at " + "FROM users WHERE id = #{id}")
    User findById(Long id);

    /**
     */
    @Select("SELECT id, username, password, email, role, created_at, updated_at " + "FROM users WHERE username = #{username}")
    User findByUsername(String username);

    /**
     */
    @Select("SELECT id, username, password, email, role, created_at, updated_at FROM users")
    List<User> findAll();

    /**
     */
    @Insert("INSERT INTO users (username, password, email, role, created_at, updated_at) " + "VALUES (#{username}, #{password}, #{email}, #{role}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(User user);

    /**
     */
    @Update("UPDATE users " + "SET password = #{password}, email = #{email}, role = #{role}, updated_at = NOW() " + "WHERE id = #{id}")
    int updateUser(User user);

    /**
     */
    @Delete("DELETE FROM users WHERE id = #{id}")
    int deleteUser(Long id);
}
