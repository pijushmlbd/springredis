package com.learning.redis.springredis.controller;

import com.learning.redis.springredis.entity.Book;
import com.learning.redis.springredis.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    LibraryService libraryService;

    /**
     * add a book in library
     * @param book
     */
    @PostMapping("/books")
    public void addUserBook(@RequestBody Book book) {
        libraryService.addBook(book);
    }

    /**
     * get the latest 10 books
     * @return
     */

    @GetMapping("/books/latest")
    public List<Book> getLatestBooks()
    {
        return libraryService.getLatestBooks();
    }


}
