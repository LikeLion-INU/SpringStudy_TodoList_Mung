package com.example.todolist.service.todo;

import com.example.todolist.common.exception.CustomException;
import com.example.todolist.common.response.ErrorCode;
import com.example.todolist.dto.todo.TodoRequestDTO;
import com.example.todolist.dto.todo.TodoResponseDTO;
import com.example.todolist.entity.member.Member;
import com.example.todolist.entity.todo.Todo;
import com.example.todolist.entity.todo.TodoStatus;
import com.example.todolist.repository.member.MemberRepository;
import com.example.todolist.repository.todo.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.todolist.entity.todo.TodoStatus.FINISH;
import static com.example.todolist.entity.todo.TodoStatus.NOT_FINISH;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class TodoServiceImpl implements TodoService {
    private final MemberRepository memberRepository;
    private final TodoRepository todoRepository;

    @Override
    @Transactional
    public TodoResponseDTO.TodoCreateDTO create(Long memberId, TodoRequestDTO.TodoCreateDTO todoCreateDTO) {
        try {
            log.info("[TodoServiceImpl] create");
            Member findMember = getMember_memberId(memberId);

            Todo todo = new Todo(todoCreateDTO.getTodoName(), findMember);
            todoRepository.save(todo);

            return new TodoResponseDTO.TodoCreateDTO(todo);
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            throw new CustomException(ErrorCode.SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public TodoResponseDTO.TodoUpdateDTO update(Long memberId, Long todoId, TodoRequestDTO.TodoUpdateDTO todoUpdateDTO) {
        try {
            log.info("[TodoServiceImpl] update");

            Member findMember = getMember_memberId(memberId);
            Todo findTodo = getTodo_todoId(todoId);

            checkMemberRelationTodo(findMember, findTodo);

            // 업데이트 하기
            findTodo.todoUpdate(todoUpdateDTO.getTodoName());

            return new TodoResponseDTO.TodoUpdateDTO(findTodo);
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            throw new CustomException(ErrorCode.SERVER_ERROR);
        }
    }

    @Override
    @Transactional
    public TodoResponseDTO.TodoToggleDTO toggle(Long memberId, Long todoId) {
        try {
            log.info("[TodoServiceImpl] toggle");

            Member findMember = getMember_memberId(memberId);
            Todo findTodo = getTodo_todoId(todoId);

            checkMemberRelationTodo(findMember, findTodo);

            toggleTodo(findTodo);

            return new TodoResponseDTO.TodoToggleDTO(findTodo);
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            throw new CustomException(ErrorCode.SERVER_ERROR);
        }

    }

    @Override
    @Transactional
    public String delete(Long memberId, Long todoId) {
        try {
            log.info("[TodoServiceImpl] delete");

            Member findMember = getMember_memberId(memberId);
            Todo findTodo = getTodo_todoId(todoId);

            checkMemberRelationTodo(findMember, findTodo);

            todoRepository.deleteById(todoId);

            return "SUCCESS";
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            throw new CustomException(ErrorCode.SERVER_ERROR);
        }
    }

    @Override
    public TodoResponseDTO.TodoFindAllDTO findAll(Long memberId) {
        try {
            log.info("[TodoServiceImpl] findAll");

            Member findMember = getMember_memberId(memberId);

            List<Todo> todoList = todoRepository.findByMemberId(memberId);
            List<TodoResponseDTO.TodoFindOneDTO> todoFindOneDTOList = new ArrayList<>();

            for(int i=0; i<todoList.size(); i++) {
                TodoResponseDTO.TodoFindOneDTO todoFindOneDTO = new TodoResponseDTO.TodoFindOneDTO(todoList.get(i));
                todoFindOneDTOList.add(todoFindOneDTO);
            }

            return new TodoResponseDTO.TodoFindAllDTO(todoFindOneDTOList);
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            throw new CustomException(ErrorCode.SERVER_ERROR);
        }
    }

    private Member getMember_memberId(Long memberId) throws CustomException {
        Optional<Member> optionalFindMember = memberRepository.findById(memberId);
        if(optionalFindMember.isEmpty()) {
            log.info("[ERROR] 존재하지 않은 회원 입니다.");
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
        }
        return optionalFindMember.get();
    }
    private void toggleTodo(Todo findTodo) throws CustomException {
        if(findTodo.getTodoStatus() == TodoStatus.NOT_FINISH) {
            findTodo.updateTodoStatus(FINISH);
        } else {
            findTodo.updateTodoStatus(NOT_FINISH);
        }
    }
    private Todo getTodo_todoId(Long todoId) throws CustomException {
        Optional<Todo> optionalFindTodo = todoRepository.findById(todoId);
        if(optionalFindTodo.isEmpty()) {
            log.info("[ERROR] 존재하지 않은 투두 입니다.");
            throw new CustomException(ErrorCode.TODO_NOT_FOUND);
        }
        return optionalFindTodo.get();
    }
    private static void checkMemberRelationTodo(Member findMember, Todo findTodo) throws CustomException {
        if(!Objects.equals(findMember.getId(), findTodo.getMember().getId())) {
            log.info("[ERROR] 투두의 주인이 아닙니다.");
            throw new CustomException(ErrorCode.ACCESS_DENIAL);
        }
    }
}
