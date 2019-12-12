package com.was.webservice.springboot.web;
//import com.was.webservice.springboot.web.dto.HelloResponseDto;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//
//// 컨트롤러를 json반환하는 컨트롤러로 만들어줌
//@RestController
//public class HelloController {
//
//    // HTTP Method인 GET의 요청을 받을 수 있는 api를 만들어줌
//    @GetMapping("/hello")
//    public String hello() {
//        return "hello";
//    }
//
//    public HelloResponseDto helloDto(@RequestParam("name") String name,
//                                     @RequestParam("amount") int amount) {
//        return new HelloResponseDto(name, amount);
//    }
//}
//
////    @RequestParam : 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
////
////    위의 코드에서는 외부에서 name (@RequestParam("name")) 이란 이름으로 넘긴 파라미터를 메소드 파라미터 name(String name)에 저장하기 됨.

import com.was.webservice.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }

}