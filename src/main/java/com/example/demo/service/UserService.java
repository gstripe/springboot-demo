package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.mapper.UserEntity;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Class Description goes here.
 *
 * @author gstripe@gmail.com
 * @date 2017-06-2017-06-12 16:18
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void saveUserList(List<User> userList) {
        for (User user : userList) {
            userRepository.save(user);
        }
    }

    @Transactional
    public void saveUserEntityList(List<UserEntity> userList) {
        for (UserEntity user : userList) {
            userMapper.insert(user);
        }
    }

}
