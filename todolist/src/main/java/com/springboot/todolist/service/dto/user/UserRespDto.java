package com.springboot.todolist.service.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserRespDto {
    private int usercode;
    private String username;
    private String password;
    private String email;
    private String name;
    private String role;
    private String provider;
    private String providerId;
}
