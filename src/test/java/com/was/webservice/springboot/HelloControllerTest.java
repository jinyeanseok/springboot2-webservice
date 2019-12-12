package com.was.webservice.springboot;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

// springboot 테스트와 JUnit 사이에 연결자 역할을 함
@RunWith(SpringRunner.class)
@WebMvcTest // web에 집중할 수 있는 어노테이션
public class HelloControllerTest {

    @Autowired /// 스프링이 관리하는 빈(Bean)을 주입받음.
    private MockMvc mvc;
    // 웹 api 테스트시 사용
    // 스프링 mvc 테스트의 시작점
    // 이 클래스를 통해 HTTP GET, POST 등에 대한 API테스트를 할 수 있음

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) // 상태 (200, 404)
                .andExpect(content().string(hello)); // perform의 상태 검증
    }
}
