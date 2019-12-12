package com.was.webservice.springboot.web;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


// 컨트롤러를 json반환하는 컨트롤러로 만들어줌
@RestController
public class HelloController {

    // HTTP Method인 GET의 요청을 받을 수 있는 api를 만들어줌
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
