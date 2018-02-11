package com.gitsome.chan.controller;

import com.gitsome.chan.core.config.WafException;
import com.gitsome.chan.entity.User;
import com.gitsome.chan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : 130801(cgs)
 * Date : 2017-09-08
 * Time : 15:07
 */
@RestController
public class IndexController {


    @Value("${spring.profiles.active}")
    private String env;



    @RequestMapping("/hello/{myName}")
    String index(@PathVariable String myName) {
        throw new WafException("BAD_REQUEST","dfdd", HttpStatus.BAD_REQUEST);
//        return "Hello "+myName+"!!!";
    }

}
