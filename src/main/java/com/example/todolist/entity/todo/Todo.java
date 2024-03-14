package com.example.todolist.entity.todo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Todo {
    @Id
    @GeneratedValue
    @Column(name = "todo_id")
    private Long id;


}
