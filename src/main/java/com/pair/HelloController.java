package com.pair;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/")
    public Demo getDemo() {
        Demo demo = new Demo();
        demo.setId(1);
        demo.setName("张三");
        return demo;
    }

}