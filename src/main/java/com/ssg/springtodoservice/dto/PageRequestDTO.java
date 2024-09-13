package com.ssg.springtodoservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    private String[] types;
    private String keyword;
    private boolean finished;
    private LocalDate from;
    private LocalDate to;





@Builder.Default //default값 지정한다~
    @Min(value=1) //1페이지부터 시작
    @Positive //양수만 허용, 음수는 안됨
    private int page=1;



    @Builder.Default//외부에서 마음대로 최소, 최대값 지정 못 하도록 default 값 지정 해 줌
    @Min(value = 10)
    @Max(value = 100)
    @Positive
    private int size = 10;//한 페이지에 보여줄 글 개수

    private String link;//페이지와 사이즈를 자동으로 공급해줄 수 있는 필드

     public int getSkip(){
         return (page-1)*10;//건너 뛸 숫자
     }

     public String getLink(){
         if(link == null){
             StringBuilder builder = new StringBuilder();
             builder.append("page="+this.page);
             builder.append("&size="+this.size);
             link = builder.toString();
         }
         return link;
     }
}
