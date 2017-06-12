package com.example.demo.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageRowBounds;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.ibatis.session.RowBounds;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Class Description goes here.
 *
 * @author gstripe@gmail.com
 * @date 2017-06-2017-06-12 10:42
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    private static final long MAX_SIZE = 100;
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void test_0() {
        for (int i = 0; i < MAX_SIZE; i++) {
            String username = RandomStringUtils.random(16, "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz");
            String password = RandomStringUtils.randomAscii(32);
            int age = RandomUtils.nextInt(1, 100);
            String email = RandomStringUtils.random(8, "1234567890abcdefghijklmnopqrstuvwxyz");
            UserEntity user = new UserEntity();
            user.setUsername(username);
            user.setPassword(password);
            // 这里如果不是5的倍数 才设置email
            if ((i + 1) % 5 != 0) {
                user.setEmail(email + "@google.com");
            }
            user.setAge(age);
            userMapper.insert(user);
        }

    }


    @Test
    public void test_getAll() throws Exception {
        // 既然分页了就不推荐使用了啊啊
        // 为何 pageSizeZero 无论设置了什么都没效果呢？
        List<UserEntity> users = userMapper.getAllByPage(new RowBounds(0, 0));
        Assert.assertEquals(MAX_SIZE, users.size());
    }


    @Test
    public void test_getAllByPage() throws Exception {

        PageRowBounds rowBounds = new PageRowBounds(20, 10);

        List<UserEntity> users = userMapper.getAllByPage(rowBounds);
        Assert.assertEquals(new Long(MAX_SIZE), rowBounds.getTotal());

        String json = mapper.writeValueAsString(users);

        System.out.println("json = " + json);

    }

    @Test
    public void test_getAllByPageParam() throws Exception {

        List<UserEntity> users = userMapper.getAllByPageParam(3, 10);

        String json = mapper.writeValueAsString(users);

        System.out.println("json = " + json);

    }


    @Test
    public void test_getAllByPagePojo() throws Exception {

        List<UserEntity> users = userMapper.getAllByPagePojo(new PagePojoParam(3, 10));

        String json = mapper.writeValueAsString(users);

        System.out.println("json = " + json);

    }

    @Test
    public void test_selectUserById() {
        UserEntity user = userMapper.selectUserById(20);
        Assert.assertEquals(new Long(20), user.getId());
    }

    @Test
    public void test_selectUserByName() throws JsonProcessingException {
        List<UserEntity> users = userMapper.findUsersByUsernameLike("%w%", new PageRowBounds(20, 10));

        String json = mapper.writeValueAsString(users);

        System.out.println("json = " + json);
        
    }


}