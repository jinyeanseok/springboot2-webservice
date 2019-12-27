# 개발환경
```
- intellij Ultimate version
- Java 8(JDK 1.8)
- Gradle 4.8
- JUnit4
```
# 단위 테스트의 장점
```
- 개발단계 초기에 문제를 발견하게 도와줌.
- 개발자가 나중에 코드를 리팩토링하거나 라이브러리 업그레이드 등에서 기존 기능이 올바르게 작동하는지 확인 가능.
- 기능에 대한 불확실성을 감소.
- 시스템에 대한 실제 문서를 제공 즉, 단위 테스트 자체가 문서로 사용할 수 있음.
- 개발자가 만든 기능을 안전하게 보호(a,b기능이 있다고 가정하면 b기능을 추가하였는데 b기능은 잘되지만 a기능에 문제가 생김)
```

# 단위 테스트를 배우기전에 진행한 개발 방식
```
1. 코드작성
2. 프로그램(Tomcat)실행
3. Postman과 같은 API 테스트 도구로 HTTP 요청
4. 요청 결과를 System.out.println()으로 검증
5. 결과가 다르면 다시 프로그램(Tomcat)을 중지하고 코드를 수정.

여기서 2 ~ 5는 코드를 수정할 때마다 반복하므로 비효율적
```


# 오류 
##### 단위 test 시 error 발생
```
intellij ultimate 2019.3 는 defalt값이 gradle이 5 version

터미널에 ./gradlew wrapper --gradle-version 4.10.2 입력하여 down grade 실행.
```

##### 한글 깨짐 해결방법
```
상단 Help - Edit Custom VM Opstion 파일에 

-Dfile.encoding=UTF-8 입력
```

##### 404 error
```
    404 에러시 2가지 문제 확인
    
    1. get url확인
    2. 폴더 위치 확인
       2.1 @SpringBootApplication으로 인해 스프링부트 자동설정, 스프링 beac 일기와 생성 모두 자동 설정
       2.2 @SpringBootApplication이 있는 위치부터 설정을 읽어나가기에 해당 클래스는 항상 프로젝트 최상단에 위치해야함.
```

##### lombok test error
```
기존 gradle의 디폴트 값이 5.12로 설정

-  ./gradlew wrapper --gradle-version 4.10.2 터미널 실행
```

##### p.111 ApiControllerTest 작성 후 기존 HelloControllerTest 깨짐
```
p.111
ApiControllerTest 작성 => 전체 테스트시 HelloControllerTest 실패
관련없는 ApiController 의 빈 생성을 시도하면서 연관된 빈을 제대로 주입받지 못해 테스트가 깨짐.
뒷장에서 추가되는건 확인.
중간중간 전체 테스트를 돌려보는 분들을 위해서
처음 작성 시 WebMvcTest의 범위를 명시해주는게 어떨까합니다.

@WebMvcTest(HelloController.class)

@WebMvcTest(HelloController.class)이 아니라 @WebMvcTest만 하면 나오는 오류
- org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name


```

##### 메서드 위에 어노테이션 확인 잘하기

##### ORM(Object Realational Mapping)
```
- MyBatis, iBatis는 ORM이 아니라 SQL Mapper.
- ORM은 객체를 매핑하는 것이고, SQL Mapper는 쿼리를 매핑.
```

# @annotation 용어(lombok포함), code설명
## 주요 어노테이션을 클래스에 가깝게 위치
##### @SpringBootApplication
```
- springboot의 자동 설정, spring Bean 읽기와 생성을 모두 자동으로 설정.
- @SpringBootApplication이 있는 위치부터 설정을 읽어가기 때문에 해당 클래스는 항상 프로젝트의 최상단에 위치.

*참고*
- SpringApplication.run으로 인해 내장 was를 실행 할 수 있음.
- 내장 was를 실행하게 되면 서버에 톰캣을 설치할 필요가 없고, 스프링부트로 만들어진 Jar파일로 실행하면 됨.
- 사용하는 이유는 '언제 어디서나 같은 환경에서 스프링 부트를 배포' 할 수 있기 때문.
```

##### @RestController
```
- 컨트롤러를 json을 반환하는 컨트롤러로 만들어줌.
- 예전에는 @ResponseBody를 각 메소드마다 선언했던 것을 한번에 사용할 수 있게 해준다고 생각하면 됨.
```

##### @GetMapping
```
- HTTP Method인 Get의 요청을 받을 수 있는 API를 만들어줌.
- 예전에는 @RequestMapping(method = RequestMethod.GET)으로 사용되었지만 이 프로젝트는 /hello로 요청이 오면 문자열 hello를
반환하는 기능을 가짐
```

##### @RunWith(SpringRunner.class)
```
- 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴.
- SpringRunner라는 스프링 실행자를 사용.
- 즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할.
```

##### @WebMvcTest
```
- 여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션.
- 선언할 경우 @Controller, @ControllerAdvice 등을 사용할 수 있음
- 단, @Service, @Component, @Respository등은 사용할 수 없음.
```

##### @Autowired
```
스프링이 관리하는 빈(Bean)을 주입 받음.
- 간단하게 말하자면 Bean은 자바 객체.
```

##### private MockMvc mvc
```
- 웹 API를 테스트할 때 사용
- 스프링 MVC 테스트의 시작점
- 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트 가능.
```

##### mvc.perform(get("/hello))
```
- MockMvc를 통해 /hello 주소로 HTTP GET 요청
- 체이닝이 지원되어 여러 검증 기능을 선언할 수 있음.
```

##### .andExpect(status().isOk())
```
- mvc.perform의 결과를 검증
- HTTP Header의 status를 검증
- 흔히 알고 있는 200, 404, 500등의 상태를 검증
```

##### .andExpect(content().string(hello))
```
- mvc.perform의 결과를 검증
- 응답 본문의 내용을 검증
- Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증
```

##### @Getter (lombok)
```
- 선언된 모든 필드의 get 메소드를 생성
```

##### @RequiredArgsConstrutor
```
- 선언된 모든 final 필드가 포함된 생성자를 생성해줌
- final이 없는 필드는 생성자에 포함되지 않음
```

##### assertThat
```
- assertj라는 테스트 검증 라이브러리의 검증 메소드
- 검증하고 싶은 대상을 메소드 인자로 받음
- 메소드 체이닝이 지원되어 isEqualTo와 같이 메소드를 이어서 사용헐 수 있음
```

##### isEaualTo
```
- assertj의 동등 비교 메소드
- assertThat에 있는 값과 isEqualTo의 값을 비교해서 같을 때만 성공.
```

##### @RequestParam
```
- 외부에서 api로 넘긴 파라미터를 가져오는 어노테이션

(@ReauestParam("name") String name)
- 이 코드와 같이 @ReauestParam("name")란 이름으로 넘긴 파라미터를 메소드 파라미터(String name)에 저장
```

##### param
```
- api 테스트할 때 사용될 요청 파라미터를 설정
- 단, 값은 String만 허용
- 숫자, 날짜도 문자열로 변경
```

##### jsonPath
```
- JSON 응답값을 필드별로 검증할 수 있는 메소드
- $를 기준으로 필드명을 명시
```

##### @Entity(JPA의 어노테이션)
```
- 테이블과 링크될 클래스
```

##### @Id
```
- 해당 테이블의 PK필드
```

##### @GeneratedValue
```
- PK의 생성 규칙
```

##### @Colume
```
- 굳이 선언하지 않아도 해당 클래스의 필드는 모두 칼럼이 됨.
- 사용하는 이유는 기본값(default)외에 추가로 변경할 옵션이 있으면 사용
```

##### @NoArgsConstructor
```
- 기본 생성자 자동 추가
- public Posts() {}와 같은 효과
```

##### @Getter
```
- 클래스 내 모든 필드의 Getter 메소드를 자동생성
```

##### @Builder
```
- 해당 클래스의 빌더 패턴 클래스를 생성
- 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
```

##### @SpringBootTest
```
- @SpringBootTest를 사용 할 경우 H2 데이터베이스를 자동으로 실행

// 실제로 실행된 쿼리를 로그로 보고 싶을 떄
src/main/resources 디렉터리 아래에 application.properties 파일을 생성하여 다음 옵션 추가
- spring.jpa.show_sql=true

// 출력되는 쿼리 로그를 MySQL 버전으로 변경
- spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect 추가
```

##### JPA 기능까지 한번에 테스트할 때
```
@SpringBootTest와 TestRestTemplate을 사용하면 됨.

@Autowired
    private TestRestTemplate restTemplate;

```
# 알게된 정보
##### javascript에서 jsp나 freemaker처럼 자바 코드를 사용할 수 있는지?
```
- 프론트엔드의 javascript가 작동하는 영역과 jsp가 작동하는 영역이 다름
- jsp를 비롯한 서버 템플릿 엔진은 서버에서 구동됨.
- 서버 템플릿 엔진을 이용한 화면 생성은 서버에서 java 코드로 문자열을 만든 뒤 이 문자열을 hrml로 변환하여 브라우저로 전달
- 결론은 서버엔진에서 javascript java code를 사용할 때 java 코드를 html로 보내고 javascript 코드는 
  '단순한 문자열'이 된다.
```

# mustache 문법
##### {#posts}}
```
- posts 라는 List를 순회합니다.
- Java의 for문과 동일하게 생각하면 됩니다.
```
##### {id}}등의 {{변수명}}
```
- List에서 뽑아낸 객체의 필드를 사용
```

##### REST에서의 CRUD HTTP Method 매핑
```
생성 (Create) - POST
읽기 (Read) - GET
수정 (Update) - PUT
삭제 (Delete) - DELETE
```

##### .gitignore가 제대로 작동하지 않을 때
```
git rm -r --cached .
git add .
git commit -m "fixed untracked files"
```
---
- gitignore가 제대로 작동되지 않아서 ignore처리된 파일이 자꾸 changes에 나올때가 있습니다.
git의 캐시가 문제가 되는거라 아래 명령어로 캐시 내용을 전부 삭제후 다시 add All해서 커밋하시면 됩니다.
---

##### intellij와 RDS mysql 연동할 때의 오류
``
The last packet sent successfully to the server was 0 milliseconds ago. The driver has not received any packets from the server.
``

##### 해결
```
AWS RDS 데이터베이스 해당 DB식별자 클릭 후 수정.
보안규칙에 기존에 있던 deafult값 + 해당 DB식별자 추가.
```
---

##### 재부팅 후 putty ec2 connection 오류
![image](https://user-images.githubusercontent.com/49624922/71176782-f3037c80-22ad-11ea-87c5-f868e1f00a9c.png)

##### 원인
```
해당 오류는 우리 컴퓨터에서 해당 DNS 주소의 서버에 접속이 되지 않아서 발생하는 문제이다. 크게는 서버가 현재 실행되지 않고 있거나,
서버의 보안 그룹 설정에 의해 현재 위치에서 해당 서버에 접속이 블록된 경우인데, 보통은 후자 문제일 확률이 높다.
```
##### 해결
```
보안 그룹 설정 메뉴에서 Add Rule을 클릭한 뒤 다음과 같이 2가지 룰을 추가
```
![image](https://user-images.githubusercontent.com/49624922/71176985-5392b980-22ae-11ea-840a-b9e7fc9b5ada.png)


##### deploy.sh에서 real profile 작성 중 오류
```
nohup java -jar \
    -Dspring.config.location=classpath:/application.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
    -Dspring.profiles.active=real \
    $REPOSITORY/$JAR_NAME 2>&1 &
```

##### deploy.sh에서 real profile 작성 중 오류 해결
```
nohup java -jar \
    -Dspring.config.location=classpath:/application.properties,classpath:/application-$IDLE_PROFILE.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
    -Dspring.profiles.active=$IDLE_PROFILE \
    $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
```

##### Travis-CI error

---
- 오류
```
/home/travis/.travis/functions: line 350: ./gradlew: Permission denied
```
- 문제
```
Windows환경에서는 문제없었지만, Travis-ci가 실행되는 Unix 환경의 exec-permission 시스템의 영향을 받아서 그렇다.
gradlew파일에 실행 권한을 주어야하는데 Unix 환경이 아니면 어떻게 할지 막막해질 수 있음.
다행스럽게도 Git에는 이에 대비할 수 있는 기능이 있음.
```
- 해결

다음 명령어로 chmod를 변경할 수 있다.

``
git update-index --chmod=+x gradlew
``
하면 적용이 된다.

``
git commit -m "permission access for travis"
``

``
git push origin master
``


다음 명령어로 Unix의 chmod를 확인해 볼 수 있다.

``
git ls-tree HEAD
``
다음과 같이 나오는 것을 확인 할 수 있다.
```
100755 blob xxxxxxxxxxx gradlew
```
---

##### git push시 error
``
Push to origin/master was rejected
``
- terminal에서 git push origin +master
```
이렇게 +를 master에 붙여주면 경고는 무시하고 데이터가 손실되도 강제로 push를 한다.
```
---

##### git travis ci deploy 오류 
- 오류 시 다음과 같은 명렁어로 로그 보기
```
tail -F /var/log/aws/codedeploy-agent/codedeploy-agent.log
```

##### git Pulling is not possible because you have unmerged files 오류
- 로컬 저장소와 원격 저장소 간데 같은 파일이 있는데, 아직 로컬 저장소에서 병합이 되지 않아 생기는 문제
```
git status
git commit -am "커밋 내용"
```
##### 궁금한 것
```
현재 프로젝트를 ec2 서버에 업로드하였고, 로컬에서는 애플리케이션 실행 시 h2-console이 동시에 활성화되었는데
ec2 서버에 올라간 애플리케이션은 왜 h2-console이 안되는지

사용자 로그인 정보가 rds쪽으로 할 수 있는지

ec2 서버에 올라간 웹서비스에 대한 사용자 로그인정보 어떻게 볼 수 있는지
EX) GUEST, USER

```
##### step2 배포 오류 해결
- 기존 code
```
  nohup java -jar \
        -Dspring.config.location=classpath:/application.properties,classpath:/application-real.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
        -Dspring.profiles.active=real \
        $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
```
- 해결한 code
```
  nohup java -jar \
      -Dspring.config.location=classpath:/application.properties,classpath:/application-$IDLE_PROFILE.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
      -Dspring.profiles.active=$IDLE_PROFILE \
      $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
```

##### home page title 변경 시 오류
```
IndexControllerTest의 
assertThat(body).contains("~~~~"); 를 title과 일치 시켜야 test에 통과하여 무중단 배포 성공
```
before_deploy:
  - mkdir -p before-deploy # zip에 포함시킬 파일들을 담을 디렉토리 생성
  - cp scripts/*.sh before-deploy/
  - cp appspec.yml before-deploy/
  - cp build/libs/*.jar before-deploy/
  - cd before-deploy && zip -r before-deploy * # before-deploy로 이동후 전체 압축
  - cd ../ && mkdir -p deploy # 상위 디렉토리로 이동후 deploy 디렉토리 생성
  - mv before-deploy/before-deploy.zip deploy/springboot2-webservice.zip # deploy로 zip파일 이동

  
  
