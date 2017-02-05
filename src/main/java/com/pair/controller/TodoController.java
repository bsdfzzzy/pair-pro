package com.pair.controller;

import com.pair.domain.Todo;
import com.pair.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zyzhao on 2/5/17.
 */
@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Todo> todos() {
        return todoRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Todo findOneTodo(@PathVariable int id) {
        return todoRepository.findOne((long) id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Object> addOneTodo(@RequestParam("title") String title) {
        if (title != "" && title != null) {
            Todo todo = new Todo();
            todo.setTitle(title);
            todo.setCompleted(false);
            todo.setCreated_at((new Date()).toString());
            todoRepository.save(todo);
            return new ResponseEntity<Object>(todo, HttpStatus.CREATED);
        } else {
            Map<String, String> json = new HashMap<String, String>();
            json.put("message", "bad title, it shouldn't be empty or null.");
            return new ResponseEntity<Object>(json, HttpStatus.BAD_REQUEST);
        }
    }

}
