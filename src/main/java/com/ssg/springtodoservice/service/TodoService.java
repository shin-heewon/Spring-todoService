package com.ssg.springtodoservice.service;

import com.ssg.springtodoservice.dto.PageRequestDTO;
import com.ssg.springtodoservice.dto.PageResponseDTO;
import com.ssg.springtodoservice.dto.TodoDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TodoService {
    void register(TodoDTO dto);

    //List<TodoDTO> getAll();

    TodoDTO getOne(Long tno);

    void remove(Long tno);

    void modify(TodoDTO dto);

    PageResponseDTO<TodoDTO> getList(PageRequestDTO pageRequestDTO);


}
