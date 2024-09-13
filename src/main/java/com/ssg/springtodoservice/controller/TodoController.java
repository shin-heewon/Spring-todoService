package com.ssg.springtodoservice.controller;

import com.ssg.springtodoservice.dto.PageRequestDTO;
import com.ssg.springtodoservice.dto.TodoDTO;
import com.ssg.springtodoservice.service.TodoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RequestMapping("/todo")
@Controller
@Log4j2
@RequiredArgsConstructor // 생성자 인젝션 받기
public class TodoController {


    private final TodoService todoService;

//    @RequestMapping("/list")
//    public void list(Model model){//스프링에서는 request에 담기 불안해서 Model 사용함?(spring-context에서 제공된다 => 가방, 즉 공간 제공)
//
//        log.info("todo list..............");
//        //model.addAttribute("dtoList", todoService.getAll());//todoService에서 getAll() 호출하면 들어올 반환값의 이름은 getAll() 리턴값과 같음
//        // 위 한 줄이 페이지 이동까지 FrontController가 처리해 주는 것과 같다. 원래는 forward/sendRedirect 해줘야 했음
//    }

    @GetMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model){
        log.info(pageRequestDTO);

        if(bindingResult.hasErrors()){
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        //model.addAttribute("requestDTO", pageRequestDTO);
        model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));
    }
    @GetMapping("/register")
    public void registerGet(){
        log.info("Get todo Register...........");
    }


    @PostMapping("/register")
    public String registerPost(@Valid TodoDTO dto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {//유효성 검증 해준다, 예외처리, 오류 없으면 redirect
        log.info("Post todo Register...........");


        if(bindingResult.hasErrors()){
            log.info("has error................");
            redirectAttributes.addFlashAttribute("error", bindingResult.getAllErrors());//????
            return "redirect:/todo/register";//에러 나면 다시 입력하도록 redirect
        }
        log.info("todoDTO  : "+dto);
        todoService.register(dto);
        return "redirect:/todo/list";
    }
    
    @GetMapping({"/read", "/modify"})//배열로 처리..?
    public void read(Long tno,PageRequestDTO pageRequestDTO, Model model){
        log.info("read...................");
        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);
        model.addAttribute("dto", todoDTO);//read.jsp로 forward 해 줌
    }

    @PostMapping("/remove")
    public String remove(Long tno,PageRequestDTO pageRequestDTO, RedirectAttributes redirectAttributes){
        log.info("remove......................");
        todoService.remove(tno);
        redirectAttributes.addAttribute("page", 1);// 삭제 후에는 무조건 첫 페이지로 이동하기~
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());

        return "redirect:/todo/list";
    }

    @PostMapping("/modify")
    public String update(@Valid TodoDTO dto,PageRequestDTO pageRequestDTO,BindingResult bindingResult, RedirectAttributes redirectAttributes){
        log.info("Post todo modify...........");
        if (bindingResult.hasErrors()){
            log.info("has error............");
            redirectAttributes.addAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("tno", dto.getTno());
            return "redirect:/todo/modify";
        }
        log.info(dto);
        todoService.modify(dto);
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());//수정 후에는 원래 있던 페이지로 돌아가라~
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());
        return "redirect:/todo/list";
    }
    
    
    
}
