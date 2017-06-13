package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;

/**
 * Class Description goes here.
 *
 * @author gstripe@gmail.com
 * @date 2017-06-08 15:16
 */
public interface UserJpaSpecificationExecutor extends Repository<User, Integer>, JpaSpecificationExecutor {

    public User save(User user);

}
