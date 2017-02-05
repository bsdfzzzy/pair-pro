package com.pair.repository;

import com.pair.domain.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by zyzhao on 2/5/17.
 */
public interface TodoService {
    Todo getTodo(String title);
}
