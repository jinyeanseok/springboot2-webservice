


## 오류 
### 단위 test 시 error 발생
```
intellij ultimate 2019.3 는 defalt값이 gradle이 5 version

터미널에 ./gradlew wrapper --gradle-version 4.10.2 입력하여 down grade 실행.
```

### 한글 깨짐 해결방법
```
상단 Help - Edit Custom VM Opstion 파일에 

-Dfile.encoding=UTF-8 입력
```

### 404 error
```
    404 에러시 2가지 문제 확인
    
    1. get url확인
    2. 폴더 위치 확인
       2.1 @SpringBootApplication으로 인해 스프링부트 자동설정, 스프링 beac 일기와 생성 모두 자동 설정
       2.2 @SpringBootApplication이 있는 위치부터 설정을 읽어나가기에 해당 클래스는 항상 프로젝트 최상단에 위치해야함.
```

### lombok test error
```
기존 gradle의 디폴트 값이 5.12로 설정

-  ./gradlew wrapper --gradle-version 4.10.2 터미널 실행
```

### 메서드 위에 어노테이션 확인 잘하기

### ORM(Object Realational Mapping)
```
- MyBatis, iBatis는 ORM이 아니라 SQL Mapper.
- ORM은 객체를 매핑하는 것이고, SQL Mapper는 쿼리를 매핑.
```