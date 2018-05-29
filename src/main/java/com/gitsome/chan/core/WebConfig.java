package com.gitsome.chan.core;

import com.gitsome.chan.core.config.ValidatorExceptionResolver;
//import com.gitsome.chan.core.config.WafRestErrorResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        // 核心线程数 线程池维护线程的最少数量
        threadPoolTaskExecutor.setCorePoolSize(100);
        // 线程池维护线程所允许的空闲时间
        threadPoolTaskExecutor.setKeepAliveSeconds(200);
        // 线程池维护线程的最大数量
        threadPoolTaskExecutor.setMaxPoolSize(1000);
        // 线程池所使用的缓冲队列
        threadPoolTaskExecutor.setQueueCapacity(20);
        return threadPoolTaskExecutor;
    }


    /**
     * 注册拦截器demo
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new AuthHandler());//注册拦截器demo
//        registry.addInterceptor(new MyLocaleChangeInterceptor());
    }

    /**
     * 配置访问html页面和静态资源扩展demo
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/index.html").addResourceLocations("/static/index.html");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/tpl/**").addResourceLocations("/tpl/");
        registry.addResourceHandler("/img/**").addResourceLocations("/img/");
    }

}
