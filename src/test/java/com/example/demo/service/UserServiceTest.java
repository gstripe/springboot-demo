package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.mapper.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Class Description goes here.
 *
 * @author gstripe@gmail.com
 * @date 2017-06-2017-06-12 16:23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void saveUserList() throws Exception {

        List<User> userList = new ArrayList<User>();

        userList.add(new User("wangjw", "123456", "wangjw@gmail.com", 33));
        userList.add(new User("wangjw2", "123456", "wangjw@gmail.com", 33));
        userList.add(new User("wangjw3333333333333333333333333333333333333", "123456", "wangjw@gmail.com", 33));
        userList.add(new User("wangjw4", "123456", "wangjw@gmail.com", 33));

        try {
            userService.saveUserList(userList);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void saveUserEntityList() throws Exception {
        List<UserEntity> userEntityList = new ArrayList<UserEntity>();

        userEntityList.add(new UserEntity("wangjw", "123456", "wangjw@gmail.com", 33));
        userEntityList.add(new UserEntity("wangjw2", "123456", "wangjw@gmail.com", 33));
        userEntityList.add(new UserEntity("wangjw3333333333333333333333333333333333333", "123456", "wangjw@gmail.com", 33));
        userEntityList.add(new UserEntity("wangjw4", "123456", "wangjw@gmail.com", 33));

        try {
            userService.saveUserEntityList(userEntityList);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}