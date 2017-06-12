package com.example.demo.repository;

import com.example.demo.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Class Description goes here.
 *
 * @author gstripe@gmail.com
 * @date 2017-06-2017-06-06 15:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRepositoryTest {

    private static final int MAX_SIZE = 100;

    @Autowired
    private UserRepository userRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void before() {

        for (int i = 0; i < MAX_SIZE; i++) {
            String username = RandomStringUtils.random(16, "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz");
            String password = RandomStringUtils.randomAscii(32);
            int age = RandomUtils.nextInt(1, 100);
            String email = RandomStringUtils.randomAscii(8);
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            // 这里如果不是5的倍数 才设置email
            if ((i + 1) % 5 != 0) {
                user.setEmail(email + "@google.com");
            }
            user.setAge(age);
            userRepository.save(user);
        }

    }

    @Test
    public void test() throws JsonProcessingException {

        System.out.println("findAllByUsernameLike...");
        List<User> users = userRepository.findAllByUsernameLike("%w%");
        for (User user : users) {
            String json = mapper.writeValueAsString(user);
            System.out.println(json);
        }

        // 大于等于n岁的
        System.out.println("findUserByAgeGreaterThanEqual...");
        users = userRepository.findUserByAgeGreaterThanEqual(95);
        for (User user : users) {
            String json = mapper.writeValueAsString(user);
            System.out.println(json);
        }

        // 查出所有的
        users = userRepository.findUsersBy();
        Assert.assertEquals(MAX_SIZE, users.size());

        /*
          page 0, index 0, size 7
          page 1, index 7, size 7
          page 2, index 14, size 7
        */

        // 第2页 10条
        System.out.println("findUsersBy pageable...");
        Pageable pageable = new PageRequest(1, 10);
        Page<User> userPage = userRepository.findUsersBy(pageable);
        String json = mapper.writeValueAsString(userPage);
        System.out.println(json);

        // 排序后分页 第3页 7条
        System.out.println("findUsersBy pageable and sort...");
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "username");
        Sort sort = new Sort(order);
        pageable = new PageRequest(2, 7, sort);
        userPage = userRepository.findUsersBy(pageable);
        json = mapper.writeValueAsString(userPage);
        System.out.println(json);



    }
}