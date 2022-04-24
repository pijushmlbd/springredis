package com.learning.redis.springredis.service.impl;

import com.learning.redis.springredis.entity.Book;
import com.learning.redis.springredis.entity.User;
import com.learning.redis.springredis.repository.UserRepository;
import com.learning.redis.springredis.service.UserService;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {


    RedisTemplate redisTemplate;
    UserRepository userRepository;
    ListOperations<String, Book> listOperations;

    private final String USER_KEY="USER";

    public UserServiceImpl(RedisTemplate redisTemplate, UserRepository userRepository) {
        this.redisTemplate = redisTemplate;
        this.userRepository = userRepository;
    }

    @Override
    public User saveUserInfo(User user) {

        User saveUser= userRepository.save(user);
        redisTemplate.opsForHash().put(USER_KEY,String.valueOf(user.getUserId()),saveUser);

        return saveUser;

    }

    @Override
    public User getUserInfo(Integer userId) {

        User user=(User) redisTemplate.opsForHash().get(USER_KEY,String.valueOf(userId));

        if(user!=null) return user;

        return userRepository.findById(userId).orElse(null);

    }



}
