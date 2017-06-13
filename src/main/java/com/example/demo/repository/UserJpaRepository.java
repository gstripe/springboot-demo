package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @see UserQueryByExampleExecutor
 *
 * @author gstripe@gmail.com
 * @date 2017-06-07 10:39
 */
public interface UserJpaRepository extends JpaRepository<User, Integer> {

}
