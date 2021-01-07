package com.dark1103.twitter.dao.repository;

import com.dark1103.twitter.dao.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByLogin(String username);

}
