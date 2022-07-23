package com.springboot.todolist.service.user;

import com.springboot.todolist.domain.user.UserRepository;
import com.springboot.todolist.service.dto.user.UserReqDto;
import com.springboot.todolist.service.dto.user.UserRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public boolean createUser(UserReqDto userReqDto) {
        return repository.save(userReqDto.toEntity()) > 0;
    }

    @Override
    public UserRespDto loadUserByUsercode(int usercode) {
        return null;
    }

    @Override
    public boolean updateUserByUsercode(int usercode) {
        return false;
    }

    @Override
    public boolean deleteUserByUsercode(int usercode) {
        return false;
    }
}
