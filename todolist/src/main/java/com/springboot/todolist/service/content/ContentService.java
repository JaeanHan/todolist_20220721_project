package com.springboot.todolist.service.content;

import com.springboot.todolist.service.dto.content.ContentReqDto;
import com.springboot.todolist.service.dto.content.ContentRespDto;

import java.util.List;

public interface ContentService {
    public boolean createTodo(ContentReqDto contentReqDto) throws Exception;

    public ContentRespDto getTodoByContentcode(int contentcode) throws Exception;
    public List<ContentRespDto> getTodoListByIndex(int index) throws Exception;

    public boolean updateTodoByContentcode(int contentcode) throws Exception;

    public boolean deleteTodoByContentcode(int contentcode) throws Exception;

}
