package com.example.todolist.dto.todo;

import com.example.todolist.dto.member.MemberResponseDTO;
import com.example.todolist.entity.member.Member;
import com.example.todolist.entity.todo.Todo;
import com.example.todolist.entity.todo.TodoStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TodoResponseDTO {
    @Data
    public static class TodoCreateDTO {
        private Long id;
        private String todoName;
        private TodoStatus todoStatus;

        public TodoCreateDTO(Todo todo) {
            this.id = todo.getId();
            this.todoName = todo.getTodoName();
            this.todoStatus = todo.getTodoStatus();
        }
    }

    @Data
    public static class TodoUpdateDTO {
        private Long id;
        private String todoName;
        private TodoStatus todoStatus;
        public TodoUpdateDTO(Todo todo) {
            this.id = todo.getId();
            this.todoName = todo.getTodoName();
            this.todoStatus = todo.getTodoStatus();
        }
    }

    @Data
    public static class TodoToggleDTO {
        private Long id;
        private String todoName;
        private TodoStatus todoStatus;
        public TodoToggleDTO(Todo todo) {
            this.id = todo.getId();
            this.todoName = todo.getTodoName();
            this.todoStatus = todo.getTodoStatus();
        }
    }

    @Data
    public static class TodoDeleteDTO {
        private Long id;
        private String todoName;
        private TodoStatus todoStatus;
        public TodoDeleteDTO(Todo todo) {
            this.id = todo.getId();
            this.todoName = todo.getTodoName();
            this.todoStatus = todo.getTodoStatus();
        }
    }
    @Data
    public static class TodoFindOneDTO {
        private Long id;
        private String todoName;
        private TodoStatus todoStatus;
        public TodoFindOneDTO(Todo todo) {
            this.id = todo.getId();
            this.todoName = todo.getTodoName();
            this.todoStatus = todo.getTodoStatus();
        }
        public TodoFindOneDTO(Long id, String todoName, TodoStatus todoStatus) {
            this.id = id;
            this.todoName = todoName;
            this.todoStatus = todoStatus;
        }
    }
    @Data
    public static class TodoFindAllDTO {
        private List<TodoFindOneDTO> todoList;
        public TodoFindAllDTO(List<TodoFindOneDTO> todoList) {
            this.todoList = todoList;
        }
    }
}
