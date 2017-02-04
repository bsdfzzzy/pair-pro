package com.pair;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by zyzhao on 2/4/17.
 */
@SpringBootApplication
public class App extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
