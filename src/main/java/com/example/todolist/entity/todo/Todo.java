package com.example.todolist.entity.todo;

import com.example.todolist.entity.base.BaseEntity;
import com.example.todolist.entity.member.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Todo extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "todo_id")
    private Long id; // 고유 식별자
    private String todoName; // 투두 제목
    private TodoStatus todoStatus = TodoStatus.NOT_FINISH; // 투두 상태

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 회원과 투두는 일대다 관계, 최대한 "다"에 단방향 관계를 해주는 게 좋음.


    /** 메서드 **/
    public void todoUpdate(String todoName) {
        this.todoName = todoName;
    }
    public void updateTodoStatus(TodoStatus todoStatus) {
        this.todoStatus = todoStatus;
    }

    /** 생성자 **/
    protected Todo() {

    }
    public Todo(String todoName) {
        this.todoName = todoName;
    }

    public Todo(String todoName, Member member) {
        this.todoName = todoName;
        this.member = member;
    }

}
