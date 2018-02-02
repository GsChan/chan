package com.gitsome.chan;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : 130801(cgs)
 * Date : 2017-06-30
 * Time : 18:32
 */
@ComponentScan("com")
@EnableAutoConfiguration
@SpringBootApplication
public class WebApplication extends SpringBootServletInitializer {
    public WebApplication() {
        this.setRegisterErrorPageFilter(false);
    }
}
