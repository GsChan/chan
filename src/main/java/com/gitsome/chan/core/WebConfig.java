package com.gitsome.chan.core;

import com.gitsome.chan.core.config.ValidatorExceptionResolver;
import com.gitsome.chan.core.config.WafRestErrorResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author : 130801(cgs)
 * Date : 2018-01-29
 * Time : 9:52
 */

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Bean
    public ValidatorExceptionResolver getValidatorExceptionResolver() {
        return new ValidatorExceptionResolver();
    }
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
//    @Bean
//    public WafRestErrorResolver wafErrorResolver() {
//        return new WafRestErrorResolver();
//    }
}
