package com.example.todolist.service.user;

import com.example.todolist.dto.user.UserRequestDTO;
import com.example.todolist.dto.user.UserResponseDTO;
import com.example.todolist.entity.user.User;
import com.example.todolist.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public UserResponseDTO.UserJoinDTO join(UserRequestDTO.UserJoinDTO userJoinDTO) {
        try {
            User user = userJoinDTO.toEntity();

            if(user.getUserEmail().equals(userJoinDTO.getUserEmail())) {
                // 중복 이메일 발생
                log.info("[ERROR] 중복 이메일 입니다.");
                return null; // 알아보기 쉽게 null로 일단 하겠습니다!
            }

            userRepository.save(user); // 중복 이메일이 없다면 DB에 저장하기.

            return new UserResponseDTO.UserJoinDTO(user);
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            return null; // 알아보기 쉽게 null로 일단 하겠습니다!
        }
    }

    @Override
    public UserResponseDTO.UserLoginDTO login(UserRequestDTO.UserLoginDTO userLoginDTO) {
        try {
            User loginUser = userLoginDTO.toEntity();
            Optional<User> optionalFindUser = userRepository.findByUserEmail(loginUser.getUserEmail());

            if(!optionalFindUser.isPresent()) {
                // 존재하지 않은 이메일
                log.info("[ERROR] 존재하지 않은 이메일 입니다.");
                return null; // 알아보기 쉽게 null로 일단 하겠습니다!
            }
            if(loginUser.getUserPassword() != optionalFindUser.get().getUserPassword()) {
                // 틀린 비밀번호
                log.info("[ERROR] 틀린 비밀번호 입니다.");
                return null; // 알아보기 쉽게 null로 일단 하겠습니다!
            }

            return new UserResponseDTO.UserLoginDTO(loginUser);
        } catch (Exception e) {
            log.info("[ERROR] Exception500");
            return null; // 알아보기 쉽게 null로 일단 하겠습니다!
        }
    }

    @Override
    public void delete(String userEmail) {

    }

    @Override
    public UserResponseDTO.UserUpdateDTO update(String userEmail) {
        return null;
    }

    @Override
    public UserResponseDTO.UserFindOneDTO findOne(Long userId) {
        return null;
    }

    @Override
    public UserResponseDTO.UserFindAllDTO findAll(String userEmail) {
        return null;
    }
}
