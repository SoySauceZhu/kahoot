package mingjie.kahoot.userservice.service;

import mingjie.kahoot.userservice.dao.UserMapper;
import mingjie.kahoot.userservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 根据ID查询用户
     */
    public User getUserById(Long id) {
        return userMapper.findById(id);
    }

    /**
     * 根据用户名查询用户
     */
    public User getUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    /**
     * 查询所有用户
     */
    public List<User> getAllUsers() {
        return userMapper.findAll();
    }

    /**
     * 新增用户
     */
    public User createUser(User user) {
        // 例如，可以在这里加一些业务逻辑：
        // 1. 检查用户名是否已存在
        // 2. 对密码进行加密/哈希
        userMapper.insertUser(user);
        return user;
    }

    /**
     * 更新用户
     */
    public int updateUser(User user) {
        // 同理，可加校验，如校验 user 是否存在，或只更新部分字段
        return userMapper.updateUser(user);
    }

    /**
     * 删除用户
     */
    public int deleteUser(Long id) {
        return userMapper.deleteUser(id);
    }
}
