package com.dark1103.twitter.service;

import com.dark1103.twitter.dao.entity.User;

public interface UserService {

    User findById(Long id);

    User findByUsername(String username);

}
