package com.was.webservice.springboot.domain.posts;


import com.was.webservice.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 클래스 내 모든 필드의 getter 메소드 자동 생성
@NoArgsConstructor // 기본생성자 자동추가, public Posts(){}와 같은 효과
@Entity // 테이블과 링크될 클래스임을 나타냄
public class Posts extends BaseTimeEntity {  // 이 클래스는 실제 db의 테이블과 매칭될 클래스이며 Entity 클래스라고 함.

    @Id // 해당 테이블의 pk필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk 생성 규칙
    private Long id;

    // Column을 굳이 선언 하지 않아도 되는데 선언하는 이유는 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {   // p.111p update메서드 누락
        this.title = title;
        this.content = content;
    }
}
