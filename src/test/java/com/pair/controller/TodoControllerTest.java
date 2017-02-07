package com.pair.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.core.Is.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
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
        mockMvc.perform(patch("/todos/8").contentType(APPLICATION_JSON).param("title", "hehehe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("hehehe")));
    }

    @Test
    public void should_return_404_after_patch() throws Exception {
        // 这里要删除的id设置为100000，假设数据库中无此id
        ResultActions actions = mockMvc.perform(patch("/todos/100000").contentType(APPLICATION_JSON).param("title", "hehehe"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("bad id,can't find the todo item")));

        // 打印响应头的信息
        // System.out.print(actions.andReturn().getResponse().getContentAsString());
    }

    @Test
    public void should_return_message_delete_success() throws Exception {
        mockMvc.perform(delete("/todos/2").contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("successfully removed the item")));
    }

    @Test
    public void should_return_404_after_delete() throws Exception {
        ResultActions actions = mockMvc.perform(delete("/todos/100000").contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("bad id,can't find the todo item to delete")));
    }
}