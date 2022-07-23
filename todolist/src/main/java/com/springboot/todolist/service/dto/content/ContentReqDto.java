package com.springboot.todolist.service.dto.content;

import com.springboot.todolist.domain.entity.Content;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContentReqDto {
    private String content;
    private int usercode;

    public Content toEntity() {
        return Content.builder()
                .content(content)
                .usercode(usercode)
                .build();
    }
}
