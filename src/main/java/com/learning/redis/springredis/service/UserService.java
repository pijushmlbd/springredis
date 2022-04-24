package com.learning.redis.springredis.service;

import com.learning.redis.springredis.entity.User;

public interface UserService {
    User saveUserInfo(User user);
    User getUserInfo(Integer userId);
}
