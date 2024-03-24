package com.example.todolist.repository.todo;

import com.example.todolist.entity.todo.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoRepositoryCustom {
    List<Todo> findByMemberId(Long memberId);
    void deleteByMemberId(Long memberId);
}
