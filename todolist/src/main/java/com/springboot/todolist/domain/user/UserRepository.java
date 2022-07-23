package com.springboot.todolist.domain.user;

import com.springboot.todolist.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
//JPARepository의 경우 CRUD 함수를 갖고있음
public interface UserRepository {
    //CRUD
    int save(User user);

    // JPA규칙 findBy 함수 (쿼리 메소드)
    //select * from user_mst where username=?
    User findByUsername(String username); //UserDetailsService
//    User getUserByUsercode(int usercode);
//    int updateUserByUsercode(int usercode);
//    int deleteUserByUsercode(int usercode);
}
