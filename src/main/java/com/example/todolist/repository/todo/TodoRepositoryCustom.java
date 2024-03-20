package com.example.todolist.repository.todo;

import com.example.todolist.dto.todo.TodoResponseDTO;

public interface TodoRepositoryCustom {
    TodoResponseDTO.TodoFindAllDTO findAllCustom(Long memberId);
}
