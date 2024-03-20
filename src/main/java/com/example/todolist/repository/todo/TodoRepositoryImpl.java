package com.example.todolist.repository.todo;

import com.example.todolist.dto.todo.TodoResponseDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;
import static com.example.todolist.entity.todo.QTodo.todo;

public class TodoRepositoryImpl implements TodoRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    public TodoRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public TodoResponseDTO.TodoFindAllDTO findAllCustom(Long memberId) {
        List<TodoResponseDTO.TodoFindOneDTO> toodList = queryFactory
                .select(Projections.constructor(TodoResponseDTO.TodoFindOneDTO.class,
                        todo.id,
                        todo.todoName,
                        todo.todoStatus
                ))
                .from(todo)
//                .leftJoin(todo.member, member)
                .where(todo.member.id.eq(memberId))
                .fetch();

        return new TodoResponseDTO.TodoFindAllDTO(toodList);
    }
}
