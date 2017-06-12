package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

/**
 * Class Description goes here.
 *
 * @author gstripe@gmail.com
 * @date 2017-06-2017-06-06 13:58
 */
public interface UserCrudRepository extends CrudRepository<User, Integer> {

}
