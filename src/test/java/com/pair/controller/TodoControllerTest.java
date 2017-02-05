package com.pair.controller;

import com.pair.App;
import com.pair.repository.TodoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.hamcrest.core.Is.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by zyzhao on 2/5/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_return_all_todo_items() throws Exception {
        mockMvc.perform(get("/todos").contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void should_return_one_todo_item() throws Exception {
        mockMvc.perform(get("/todos/2").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    public void should_return_this_todo_item_when_post() throws Exception {
        mockMvc.perform(post("/todos").contentType(APPLICATION_JSON).param("title", "newTodo"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is("newTodo")));
    }

    @Test
    public void should_return_todo_item_after_patch() throws Exception {
        mockMvc.perform(patch("/todos/2").contentType(APPLICATION_JSON).param("title", "hehehe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("hehehe")));
    }

    @Test
    public void should_return_message_delete_success() throws Exception {
        mockMvc.perform(delete("/todos/2").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("successfully removed the item")));
    }
}