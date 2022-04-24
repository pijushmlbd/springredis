package com.learning.redis.springredis.service.impl;

import com.learning.redis.springredis.entity.Book;
import com.learning.redis.springredis.entity.UserBook;
import com.learning.redis.springredis.entity.dto.UserBookDto;
import com.learning.redis.springredis.repository.BookRepository;
import com.learning.redis.springredis.repository.UserBookRepository;
import com.learning.redis.springredis.service.LibraryService;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LibraryServiceImpl implements LibraryService {


    private final String LATEST_BOOKS = "latest_books";
    private final String CURRENT_READ = "current_read";

    RedisTemplate redisTemplate;
    BookRepository bookRepository;
    UserBookRepository userBookRepository;

    ListOperations<String, Book> listOperations;


    public LibraryServiceImpl(RedisTemplate redisTemplate, BookRepository bookRepository,UserBookRepository userBookRepository) {
        this.redisTemplate = redisTemplate;
        this.bookRepository = bookRepository;
        this.userBookRepository=userBookRepository;
    }


    @Override
    public void addBook(Book book) {

        bookRepository.save(book);
        addToLatestBookList(book);
    }

    public List<Book> getLatestBooks() {
        listOperations = redisTemplate.opsForList();
        return listOperations.range(LATEST_BOOKS, 0, 9);
    }

    @Override
    public List<Book> getHigestRatedBooks() {

        //TODO add to sortedsets may be
        return null;
    }

    @Override
    public List<Book> userBooks(Integer userid) {
        List<Book> books = new ArrayList<>();
        System.out.println(redisTemplate.opsForHash().get("BOOK", userid));
        return books;
    }

    @Override
    public void addUserBook(UserBookDto userBookDto, Integer userId, Integer bookId) {

         Optional<Book> optBook=bookRepository.findById(bookId);

         if(optBook.isPresent())
         {
             UserBook userBook=new UserBook();
             userBook.setUserId(userId);
             userBook.setBookId(bookId);
             userBook.setIsFavourite(userBookDto.getIs_favourite());
             userBook.setReadingStatus(userBookDto.getReading_status());

             userBookRepository.save(userBook);

             listOperations = redisTemplate.opsForList();

             listOperations.leftPush(userId+""+CURRENT_READ,optBook.get());

         }


    }

    public List<Book> getCurrentlyReadingBook(Integer userId)
    {
        listOperations = redisTemplate.opsForList();
        return listOperations.range(userId+""+CURRENT_READ,0,9);
    }


    public void addToLatestBookList(Book book) {

        listOperations = redisTemplate.opsForList();
        listOperations.leftPush(LATEST_BOOKS, book);
        listOperations.trim(LATEST_BOOKS, 0, 10);

    }
}
