package com.springboot.todolist.web.api.content;

import com.springboot.todolist.service.content.ContentService;
import com.springboot.todolist.service.dto.content.ContentReqDto;
import com.springboot.todolist.service.dto.content.ContentRespDto;
import com.springboot.todolist.web.api.dto.CMRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/todolist")
@RequiredArgsConstructor
public class ContentController {

    private final ContentService service;

    @PostMapping("content")
    public ResponseEntity<?> addTodo(@RequestBody ContentReqDto contentReqDto) {
        boolean result = false;
        try {
            result = service.createTodo(contentReqDto);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok().body(new CMRespDto<>(-1, "등록 실패", false));
        }
        return ResponseEntity.ok().body(new CMRespDto<>(1, "등록 성공", result));
    }

    @GetMapping("content/{contentcode}")
    public ResponseEntity<?> getTodo(@PathVariable int contentcode) {
        ContentRespDto content;
        try {
            content = service.getTodoByContentcode(contentcode);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok().body(new CMRespDto<>(-1, "조회 실패", null));
        }
        return ResponseEntity.ok().body(new CMRespDto<>(1, "조회 성공", content));
    }

    @GetMapping("list/{index}")
    public ResponseEntity<?> getList(@PathVariable int index) {
        List<ContentRespDto> todoList = new ArrayList<>();
        try {
            service.getTodoListByIndex(index).forEach(todo -> {
                todoList.add(todo);
            });
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok().body(new CMRespDto<>(-1, "조회실패", false));
        }
        return ResponseEntity.ok().body(new CMRespDto<>(1, "조회성공", todoList));
    }
}
