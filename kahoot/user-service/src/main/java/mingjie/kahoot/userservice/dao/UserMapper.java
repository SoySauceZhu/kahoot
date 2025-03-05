package mingjie.kahoot.userservice.dao;

import mingjie.kahoot.userservice.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 基于注解的 MyBatis Mapper 示例
 */
@Mapper
public interface UserMapper {

    /**
     * 根据主键ID查询用户
     */
    @Select("SELECT id, username, password, email, role, created_at, updated_at " + "FROM users WHERE id = #{id}")
    User findById(Long id);

    /**
     * 根据用户名查询用户
     */
    @Select("SELECT id, username, password, email, role, created_at, updated_at " + "FROM users WHERE username = #{username}")
    User findByUsername(String username);

    /**
     * 查询所有用户
     */
    @Select("SELECT id, username, password, email, role, created_at, updated_at FROM users")
    List<User> findAll();

    /**
     * 插入新用户
     * useGeneratedKeys = true、keyProperty = "id" 可让 MyBatis 自动回填自增主键
     */
    @Insert("INSERT INTO users (username, password, email, role, created_at, updated_at) " + "VALUES (#{username}, #{password}, #{email}, #{role}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(User user);

    /**
     * 更新用户
     */
    @Update("UPDATE users " + "SET password = #{password}, email = #{email}, role = #{role}, updated_at = NOW() " + "WHERE id = #{id}")
    int updateUser(User user);

    /**
     * 删除用户
     */
    @Delete("DELETE FROM users WHERE id = #{id}")
    int deleteUser(Long id);
}
