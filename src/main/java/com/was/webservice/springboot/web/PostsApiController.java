package com.was.webservice.springboot.web;


import com.was.webservice.springboot.service.posts.PostsService;
import com.was.webservice.springboot.web.dto.PostsResponseDto;
import com.was.webservice.springboot.web.dto.PostsSaveRequestDto;
import com.was.webservice.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {      // 수정, 조회 기능

    private final PostsService postsService;

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

}
