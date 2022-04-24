package com.learning.redis.springredis.controller;

import com.learning.redis.springredis.entity.Book;
import com.learning.redis.springredis.entity.User;
import com.learning.redis.springredis.entity.dto.UserBookDto;
import com.learning.redis.springredis.service.LibraryService;
import com.learning.redis.springredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {


        @Autowired
        LibraryService libraryService;

        @Autowired
        UserService userService;

        /**
         * save userinfo
         * @param user
         */

        @PostMapping("/users/")
        public User userLogin(@RequestBody User user)
        {
             return userService.saveUserInfo(user);
        }

        @GetMapping("/users/{userid}")
        public User getUserInfo(@PathVariable("userid") Integer userId)
        {
               return userService.getUserInfo(userId);
        }

        /**
         *     update user info for a particular book like current reading status
         * @param userBookDto
         * @param userid
         * @param bookId
         */

        @PostMapping("/users/{userid}/books/{bookid}")
        public void addUserBook(@RequestBody UserBookDto userBookDto, @PathVariable("userid") Integer userid, @PathVariable("bookid") Integer bookId)
        {
                libraryService.addUserBook(userBookDto,userid,bookId);
        }

        @GetMapping("/users/{userid}/books/current_read")
        public List<Book> getCurrentlyReading( @PathVariable("userid") Integer userid)
        {
              return   libraryService.getCurrentlyReadingBook(userid);
        }


}
