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

    public User getUserById(Long id) {
        return userMapper.findById(id);
    }

    public User getUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userMapper.findAll();
    }

    public User createUser(User user) {
        userMapper.insertUser(user);
        return user;
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public void deleteUser(Long id) {
        userMapper.deleteUser(id);
    }
}
