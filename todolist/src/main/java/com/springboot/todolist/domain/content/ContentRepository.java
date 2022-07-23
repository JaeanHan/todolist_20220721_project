package com.springboot.todolist.domain.content;

import com.springboot.todolist.domain.entity.Content;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ContentRepository {
    public int save(Content content);

    public Content readContentByContentCode(int contentcode) throws Exception;
    public List<Content> readContentListByIndex(int index);

    int updateByContentcode(int contentcode);

    int deleteByContentcode(int contentcode);
}
