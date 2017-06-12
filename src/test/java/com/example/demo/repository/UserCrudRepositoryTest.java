package com.example.demo.repository;

import com.example.demo.domain.User;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Class Description goes here.
 *
 * @author gstripe@gmail.com
 * @date 2017-06-2017-06-06 15:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserCrudRepositoryTest {

    @Autowired
    private UserCrudRepository userCrudRepository;

    @Test
    public void test_0() {
        User user = new User();
        user.setUsername("wangjw");
        user.setPassword("123456");
        user.setEmail("email@sina.com");
        user.setAge(99);

        User saved = userCrudRepository.save(user);

        Assert.assertNotNull(saved);

        user = new User();
        user.setUsername("wangjw2");
        user.setPassword("123456");
        user.setEmail("email2@sina.com");
        user.setAge(23);

        saved = userCrudRepository.save(user);

        Assert.assertNotNull(saved);

    }

    @Test
    public void test_findOne() {
        User one = userCrudRepository.findOne(1);
        Assert.assertNotNull(one);
        Assert.assertEquals(one.getUsername(), "wangjw");
    }

    @Test
    public void test_del() {
        userCrudRepository.delete(2);
    }

    @Test
    public void test_existsById() {
        boolean existsBy_0 = userCrudRepository.exists(0);
        Assert.assertFalse(existsBy_0);

        boolean existsBy_1 = userCrudRepository.exists(1);
        Assert.assertTrue(existsBy_1);

    }

}