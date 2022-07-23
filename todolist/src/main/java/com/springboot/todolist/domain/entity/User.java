package com.springboot.todolist.domain.entity;

import com.springboot.todolist.service.dto.user.UserRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {
    private int usercode;
    private String username;
    private String password;
    private String name;
    private String email;
    private String role;
    private String provider;
    private String providerId;
    private LocalDateTime createdate;
    private LocalDateTime updatedate;

    public UserRespDto toRespDto() {
        return UserRespDto.builder()
                .usercode(usercode)
                .username(username)
                .password(password)
                .name(name)
                .email(email)
                .role(role)
                .provider(provider)
                .providerId(providerId)
                .build();
    }
}
