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

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Object> updateTodo(@RequestParam("title") String title, @RequestParam(value = "completed", required = false) String completed, @PathVariable int id) {
        Map<String, String> json = new HashMap<String, String>();
        Todo todo = todoRepository.findOne((long) id);
        if (title != "" && title != null) {
            todo.setTitle(title);
            if (completed != null) {
                if (completed == "false") {
                    todo.setCompleted(false);
                } else if (completed == "true") {
                    todo.setCompleted(true);
                }
            }
            todoRepository.save(todo);
            return new ResponseEntity<Object>(todo, HttpStatus.OK);
        } else {
            json.put("message", "bad title, it shouldn't be empty or null.");
            return new ResponseEntity<Object>(json, HttpStatus.BAD_REQUEST);
        }
//        if (!have) {
//            json.put("message", "can't find the todo item");
//            return new ResponseEntity<Object>(json, HttpStatus.NOT_FOUND);
//        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteTodoItem(@PathVariable int id) {
        Map<String, String> json = new HashMap<String, String>();
        todoRepository.delete((long) id);
        json.put("message", "successfully removed the item");
        return new ResponseEntity<Object>(json, HttpStatus.OK);
    }

}
