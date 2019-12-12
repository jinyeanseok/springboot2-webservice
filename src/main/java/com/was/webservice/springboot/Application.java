package com.was.webservice.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// 스프링부트의 자동설정, 스프링 Bean 읽기와 생성 모두 자동설정
// @SpringbootApplication이 있는 위치부터 설정을 읽어가기에 해당 클래스는 항상 프로젝트의 최상단에 위치.
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        // SpringApplication.run으로 인해 내장 was(톰캣)를 실행
    }
}
