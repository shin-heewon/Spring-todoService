package com.ssg.springtodoservice.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// ModelMapperConfig는 기존의 MapperUtil 클래스를 스프링으로 변경한 버전을
// @Configuration 어노테이션을 이용하여 스프링 컨테이너 빈으로 등록하여 필요할 때 사용한다.
@Configuration//해당 클래스가 스프링 빈에 대한 설정을 하는 클래스임을 명시한다.
public class ModelMapperConfig {
    @Bean // 해당 getMapper()의 실행 결과로 반환된 객체를 스프링의 빈(Bean)으로 등록시키는 역할
    public ModelMapper getMapper(){

            ModelMapper modelMapper = new ModelMapper();
            modelMapper.getConfiguration()
                    .setFieldMatchingEnabled(true)
                    .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                    .setMatchingStrategy(MatchingStrategies.STRICT);
            return modelMapper;
    }


}
