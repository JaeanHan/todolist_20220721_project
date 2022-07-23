package com.springboot.todolist.domain.entity;

import com.springboot.todolist.service.dto.content.ContentRespDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    private int contentcode;
    private String content;
    private int usercode;
    private LocalDateTime createdate;
    private LocalDateTime updatedate;

    public ContentRespDto toRespDto() {
        return ContentRespDto.builder()
                .contentcode(contentcode)
                .content(content)
                .usercode(usercode)
                .build();
    }
}
