package com.pair.repository;

import com.pair.domain.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by zyzhao on 2/5/17.
 */
@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {
    Page<Todo> findAll(Pageable pageable);
    Todo findByTitle(String title);
}
