package com.example.todolist.entity.todo;

import com.example.todolist.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Todo extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "todo_id")
    private Long id; // 고유 식별자
    private String todoName; // 투두 제목
    private todoStatus todoStatus; // 투두 상태
}
