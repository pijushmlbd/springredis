package com.learning.redis.springredis.repository;

import com.learning.redis.springredis.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User,Integer> {

}
