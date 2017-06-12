package com.example.demo.repository;

import com.example.demo.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Class Description goes here.
 *
 * @author gstripe@gmail.com
 * @date 2017-06-2017-06-07 13:58
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserQueryByExampleExecutorTest {

    @Autowired
    private UserQueryByExampleExecutor userQueryByExampleExecutor;

    @Test
    public void test_existsByExample() {
        // 创建查询条件的数据对象
        User condition = new User();
        condition.setUsername("wang");
        condition.setId(1);

        // 创建匹配器
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("id", ExampleMatcher.GenericPropertyMatchers.exact());

        // 创建查询实例
        Example<User> example = Example.of(condition, matcher);

        boolean exists = userQueryByExampleExecutor.exists(example);

        Assert.assertTrue(exists);

    }

}