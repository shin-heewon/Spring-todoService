package com.ssg.springtodoservice.mapper;

import com.ssg.springtodoservice.domain.TodoVO;
import com.ssg.springtodoservice.dto.PageRequestDTO;
import com.ssg.springtodoservice.dto.PageResponseDTO;
import com.ssg.springtodoservice.dto.TodoDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@Log4j2
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
@ExtendWith(SpringExtension.class) // 외부에서 끌어옴?
class TodoMapperTest {

    @Autowired(required = false)
    private TodoMapper todoMapper;
    @Test
    void getTime() {
        log.info(todoMapper.getTime());
    }

    @Test
    public void testInsert(){
        TodoVO vo = TodoVO.builder().title("스프링테스트").dueDate(LocalDate.of(2024,9,12)).writer("ssg").build();
        todoMapper.insert(vo);
    }


    @Test
    public void testSelectAll(){
        List<TodoVO> voList = todoMapper.selectAll();
        voList.forEach(vo->log.info(vo));
    }

    @Test
    public void testSelectOne(){
        TodoVO vo= todoMapper.selectOne(3l);
        log.info(vo);

    }

    @Test
    public void testDelete(){
        todoMapper.delete(7l);
    }

    @Test
    public void testUpdate(){
        TodoVO vo = TodoVO.builder().title("수정되나??").dueDate(LocalDate.of(2024,9,15)).tno(1l).build();
        todoMapper.update(vo);
    }

    @Test
    public void testSelectList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(2).size(10).build();
        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
        voList.forEach(vo ->log.info(vo));
    }

    @Test
    public void testSelectSearch(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).types(new String[]{"t","w"}).keyword("ssg").build();
        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
        voList.forEach(vo -> log.info(vo));
    }


}