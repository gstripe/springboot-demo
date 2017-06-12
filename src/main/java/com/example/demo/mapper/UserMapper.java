package com.example.demo.mapper;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageRowBounds;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 *
 * mybatis 的 mapper是不支持重载的
 * @author gstripe@gmail.com
 * @date 2017-06-2017-06-12 10:28
 */
@Mapper
public interface UserMapper {

    @Insert("insert into T_USER(USERNAME, PASSWORD, EMAIL, AGE) values (#{username}, #{password}, #{email}, #{age})")
    void insert(UserEntity user);

    @Select("select * from T_USER order by USERNAME")
    public List<UserEntity> getAllByPage(RowBounds rowBounds);

    @Select("select * from T_USER order by USERNAME")
    public List<UserEntity> getAllByPageParam(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    /**
     * 演示用，不要用
     * @param pagePojoParam
     * @return
     */
    @Select("select * from T_USER order by USERNAME")
    public List<UserEntity> getAllByPagePojo(PagePojoParam pagePojoParam);

    public UserEntity selectUserById(Integer id);

    /**
     * 使用Mapper.xml接口配置的时候 需要使用RowBounds做为分页参数，
     * 不要使用PagePojoParam之类来进行，
     * 不要使用@Param("pageNum") int pageNum, @Param("pageSize") int pageSize
     * 似乎有问题
     * 可以使用 {@link com.github.pagehelper.PageHelper}
     * @param username
     * @param rowBounds
     * @return
     */
    public List<UserEntity> findUsersByUsernameLike(String username, RowBounds rowBounds);

}
