package com.ssg.springtodoservice.mapper;

import com.ssg.springtodoservice.dto.PageRequestDTO;
import com.ssg.springtodoservice.dto.PageResponseDTO;
import com.ssg.springtodoservice.dto.TodoDTO;
import com.ssg.springtodoservice.service.TodoService;
import com.ssg.springtodoservice.service.TodoServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@Log4j2
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
@ExtendWith(SpringExtension.class)
public class TodoServiceTests {

    @Autowired(required = false)
    private TodoServiceImpl todoService;

    @Test
    public void testRegister(){

        TodoDTO dto = TodoDTO.builder().title("스프링테스트").dueDate(LocalDate.of(2024,9,12)).writer("ssg").build();
        todoService.register(dto);
    }

    @Test
    public void testPaging(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
        PageResponseDTO<TodoDTO> responseDTO = todoService.getList(pageRequestDTO);
        log.info(responseDTO);

        //responseDTO.getDtoList().stream().forEach(dto -> log.info(dto));
    }
}
