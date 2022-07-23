package com.springboot.todolist.service.dto.content;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContentRespDto {
    private int contentcode;
    private String content;
    private int usercode;
}
