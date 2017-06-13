package com.example.demo.repository;

import com.example.demo.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.*;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Class Description goes here.
 *
 * @author gstripe@gmail.com
 * @date 2017-06-08 15:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserJpaSpecificationExecutorTest {

    @Autowired
    private UserJpaSpecificationExecutor userJpaSpecificationExecutor;

    @Test
    public void test_0() {
        User user = new User();
        user.setUsername("wangjw");
        user.setPassword("123456");
        user.setEmail("email@sina.com");
        user.setAge(99);

        User saved = userJpaSpecificationExecutor.save(user);

        Assert.assertNotNull(saved);
    }

    /*
        过滤条件
        1：过滤条件会被应用到SQL语句的FROM子句中。在criteria 查询中，查询条件通过Predicate或Expression实例应用到CriteriaQuery对象上。
        2：这些条件使用 CriteriaQuery .where 方法应用到CriteriaQuery 对象上
        3：CriteriaBuilder也作为Predicate实例的工厂，通过调用CriteriaBuilder 的条件方法（ equal，notEqual， gt， ge，lt， le，between，like等）创建Predicate对象。
        4：复合的Predicate 语句可以使用CriteriaBuilder的and, or andnot 方法构建。
     */
    @Test
    public void test_findAllBySpecification() throws JsonProcessingException {

        /**
         * 1.
         * {@link Root} 查询结果的一个实体对象， 其中一对多或者多对一就是从这个对象开始计算的，具体层次关系
         * {@link javax.persistence.TupleElement}
         * {@link Selection}
         * {@link Expression}
         * {@link Path}
         * {@link From}
         * {@link Root}
         * 这几个接口主要用于描述一个数据库对象与实体对象的对应关系
         *
         * 2.
         * {@link CriteriaQuery} JPA标准，用来构建查询条件；
         * 查询方式：distinct、select、where、groupby、having、orderby；
         * {@link CriteriaBuilder} 进行函数操作？
         */
        List<User> userList = userJpaSpecificationExecutor.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                /*
                Path<String> path1 = root.get("username");
                Path<Integer> path2 = root.get("age");
                Predicate p1 = cb.like(path1, "%w%");
                Predicate p2 = cb.gt(path2, 95);
                */

                Expression<String> e1 = root.get("username").as(String.class);
                Expression<Integer> e2 = root.get("age").as(Integer.class);
                Predicate p1 = cb.like(e1, "%w%");
                Predicate p2 = cb.gt(e2, 95);
                // 添加查询条件到where
                query.where(cb.and(p1, p2));
                // 来个排序
                query.orderBy(cb.asc(root.get("id").as(Integer.class)));
                return query.getRestriction();
            }

            // Root 查询哪个表
            // CriteriaQuery 查询那些字段，排序是什么
            // CriteriaBuilder 字段之间的关系是什么， 如何生成一个查询条件，每一个查询条件都是什么方式
            // Predicate(Expression的子接口) 单独每一条查询条件的详细描述

        });

        String json = new ObjectMapper().writeValueAsString(userList);

        System.out.println("json = " + json);

    }


}