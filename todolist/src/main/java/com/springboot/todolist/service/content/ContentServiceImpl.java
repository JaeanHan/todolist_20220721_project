package com.springboot.todolist.service.content;

import com.springboot.todolist.domain.content.ContentRepository;
import com.springboot.todolist.domain.entity.Content;
import com.springboot.todolist.service.dto.content.ContentReqDto;
import com.springboot.todolist.service.dto.content.ContentRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService{

    private final ContentRepository repository;

    @Override
    public boolean createTodo(ContentReqDto contentReqDto) throws Exception {
        return (repository.save(contentReqDto.toEntity())) > 0;
    }

    @Override
    public ContentRespDto getTodoByContentcode(int contentcode) throws Exception {
        ContentRespDto contentRespDto = null;

        Content entity  = repository.readContentByContentCode(contentcode);
        contentRespDto = entity.toRespDto();
        return contentRespDto;
    }

    @Override
    public List<ContentRespDto> getTodoListByIndex(int index) throws Exception {
        List<ContentRespDto> contentRespDtos = new ArrayList<>();

        repository.readContentListByIndex(index).forEach(content -> {
            System.out.println(content);
            contentRespDtos.add(content.toRespDto());
        });

        return contentRespDtos;
    }

    @Override
    public boolean updateTodoByContentcode(int contentcode) throws Exception {
        return false;
    }

    @Override
    public boolean deleteTodoByContentcode(int contentcode) throws Exception {
        return false;
    }
}
