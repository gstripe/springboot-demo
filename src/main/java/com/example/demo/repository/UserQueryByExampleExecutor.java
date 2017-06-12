package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * 实例查询
 *
 * 特点与约束 （限制还是挺多的）
 * 1. 支持 动态查询 属性值为 null 则忽略过滤条件（可以配置对属性值null的处理方式）
 * 2. 不支持 不支持or 只支持 and
 * 3. 对字符串 开始 包含 结束 正则表达式匹配 和 其他属性类型的精确匹配
 *
 * @author gstripe@gmail.com
 * @date 2017-06-2017-06-07 13:57
 */
public interface UserQueryByExampleExecutor extends Repository<User, Integer>, QueryByExampleExecutor<User> {
}
