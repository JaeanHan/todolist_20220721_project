package com.springboot.todolist.service.user;

import com.springboot.todolist.service.dto.user.UserReqDto;
import com.springboot.todolist.service.dto.user.UserRespDto;

public interface UserService {
    boolean createUser(UserReqDto userReqDto);

    UserRespDto loadUserByUsercode(int usercode);

    boolean updateUserByUsercode(int usercode);

    boolean deleteUserByUsercode(int usercode);

}
