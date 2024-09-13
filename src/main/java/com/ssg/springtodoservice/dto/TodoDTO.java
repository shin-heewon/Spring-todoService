package com.ssg.springtodoservice.dto;

import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
// 스프링 MVC에서는 파라미터 검증 작업은 컨트롤러에서 한다!!!!
// @Valid 와 BindingResult 객체를 이용하여 처리한다.
//hibernate-validate 라이브러리가 필요하다.
public class TodoDTO {

        private Long tno;
        
        @NotEmpty // null 허용하지 않음
        private String title;
        
        @Future // local date 기준 미래 시점 날짜만 허요
        private LocalDate dueDate;
        
        @NotEmpty
        private String writer;
        private boolean finished;


}
