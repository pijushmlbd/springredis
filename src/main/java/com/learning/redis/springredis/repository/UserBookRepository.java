package com.learning.redis.springredis.repository;

import com.learning.redis.springredis.entity.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBookRepository extends JpaRepository<UserBook, Integer> {
}
