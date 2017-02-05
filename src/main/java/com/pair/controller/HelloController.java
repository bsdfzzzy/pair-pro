package com.pair.controller;

import com.pair.Demo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
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

    @RequestMapping(value = "hello", method = RequestMethod.POST)
    public void postHello(@RequestBody String a) {

    }

}