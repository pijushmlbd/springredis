package com.learning.redis.springredis.service;

import com.learning.redis.springredis.entity.Book;
import com.learning.redis.springredis.entity.dto.UserBookDto;

import java.util.List;

public interface LibraryService {

       void addBook(Book book);
       List<Book> userBooks(Integer userid);
       void addUserBook(UserBookDto userbook, Integer userId, Integer bookId);
       List<Book> getLatestBooks();
       List<Book> getHigestRatedBooks();
       List<Book> getCurrentlyReadingBook(Integer userId);
}
