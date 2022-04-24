package com.learning.redis.springredis.repository;

import com.learning.redis.springredis.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository  extends JpaRepository<Book,Integer> {
}
