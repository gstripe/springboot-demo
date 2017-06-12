package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;

/**
 * {@link Repository} 示例
 *
 * @author gstripe@gmail.com
 * @date 2017-06-2017-06-07 14:29
 */
public interface UserRepository extends Repository<User, Integer> {

    /**
     * 保存一个用户
     * 批量好像这里不支持？
     *
     * @param user
     * @return
     */
    public User save(User user);

    /**
     * 模糊查询用户名
     *
     * @param username
     * @return
     */
    public List<User> findAllByUsernameLike(String username);

    /**
     * 找出年龄大于等于n的记录
     *
     * @param age
     * @return
     */
    public List<User> findUserByAgeGreaterThanEqual(int age);

    /**
     * 查询所有的记录
     *
     * @return
     */
    public List<User> findUsersBy();

    /**
     * 分页查询
     * @param pageable
     * @return
     */
    public Page<User> findUsersBy(Pageable pageable);

}
