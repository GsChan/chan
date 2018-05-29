package com.gitsome.chan.controller;

import com.gitsome.chan.core.constant.Constants;
import com.gitsome.chan.entity.User;
import com.gitsome.chan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.org.mozilla.javascript.internal.Function;

import java.util.UUID;

/**
 * @author : 130801(cgs)
 * Date : 2018-01-29
 * Time : 10:49
 */
@RestController
@RequestMapping(Constants.V1+"/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/{user_id}",method = RequestMethod.GET)
    public User getUser(@PathVariable("user_id")UUID userId){
        throw new RuntimeException("aaa");
//        return userRepository.findOne(userId);
    }
    @RequestMapping(value = "",method = RequestMethod.POST)
    public User insert(@RequestBody User user){
        userRepository.save(user);
        return user;
    }

}
