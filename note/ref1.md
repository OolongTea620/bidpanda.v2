## 싱글벙글 리펙토링

#### 1.코드 리펙토링 목적

    1. Service layter에서 DIP를 적용하여 controller-service-dao-dto사이의 코드 의존성 줄이기
    2. 변경된 리펙토링에 따라서 기능 모듈 테스트 추가하기

#### 2.패키지 구조

파일 구조 변경이 당연할가 고민을 하다가 블로그 글을 보면서 레퍼런스 체크를 했다.
그 중에서 다음과 같은 패키지 구조를 채택하게 되었다.

```text
user/
    entity/
        model/
            User.java
        exception/
            UserNotFoundException.java
            ...
        gateway/
            UserGateWay.java
    usecase/
        impl/
            createUserUseCaseImpl.java
            ...
        dto/
            IUserInfoPublicData.java
            ...
        createUserUseCase.java
        ...
    infrastructure/
        api/
            web/
                UserApiController.java
                ...
        dto/
            UserCreateSuccessPublicData.java
            UserInfoPublicData.java
            ...
        gateway/
            AdminDatabaseGateway.java
        db/
            repository/
                AdminRepository.java
            jpa/
               UserJpaEntity.java 
```

### 리펙토링 기록

#### 2024.02.26
* 다시 도메인 세우고 추가된 User 도메인 유닛 테스트 작성

---
#### 2024.02.25
* Verification이 적용된 회원가입 로직 완성
* Verification시, Dto에서 Dto 생성 관련 오류 있었음 (지금도 이유 추적을 못함)

#### 2024.02.24
* Redis Repository CRUD 작성 -> String Value 형식으로 진행함. TTL 5분으로 진행

---
#### 2024.02.23
* Verification 미적용된 유저 Service 작성 완
* Google 이메일 SMTP 프로토콜 통신 추가, 연동

#### 2024.02.22
* 일단 패키지를 재수정. 재 수정한 패키지가 지금 상황에서 최선이라고 생각됨
* TDD의 장점을 이렇게 생각함 -> 코드 수정이 일어나도, 도메인만 바뀌지 않는다면, 테스트 자체가 명세의 성격을 가진다?

---
#### 2024.02.21
* 오늘은 쉬었다.

---
#### 2024.02.15

* 헥사고날 아키텍처 도입 고려, 패키지 구조 재정의
* 유저 로그인 서비스 로직 작성
* 경매, 입찰 비즈니즈 요구사항 정리
* 로그인을 위한 Oauth 학습
* JWT 복습
* User 도메인 코드 재배치

---

#### 2024.02.14

* 경매 기능 작성, 경매 비즈니스 명세 정의
* 막상 한번 User 도메인으로 완성을 하려니, 생각보다 패키지 구조가 복잡함을 인지
* 코드 진행 속도가 나지 않아서 -> 클린이 아닌 헥사고날 변경 고려

---

#### 2024.02.13

* user 회원 가입과, 유저 정보 조회, 유저 탈퇴(가입 취소) 도메인 작성, 테스트

---

#### 2024.02.12

* user 회원 가입 기능 완성 , Test 토입

---

#### 2024.02.11

* 패키지 구조 확정
* 엔티티 -> User 도메인 작성 시작

---

#### 2024.02.10

* User 엔티티 작성
* 패키지 구조 계획 세우기

---

#### 2024.02.09

* 생각보다 user 도메인 분리가 어려운 것 같다. 이유 : spring security, 다른 도메인으로 관여가 많은 도메인이라서
* User 와 UserCreate, UserUpdate 엔티티 독립 고민 -> 클린 아키텍처 용어 정의를 생각해 볼 때, 분리하는게 맞다고 판단
* 좀 더 서치한 결과로, 헥사고날 -> 클린 아키텍처로 이론이 발전되었음을 발견
* 유스케이스 명세 작성

---

#### 2024.02.08

* User 버전 리펙토링 시작

---

#### 2024.02.06

* User 도메인 부분 재작성
* 헥사고날 방식과 클린 아키텍처 학습 시작, 패키지 구조 검색 시작

---

#### 2024.02.05

* 내가 만든 STOMP 적용 채팅의 문제 인식 -> 코드 수정이 어렵다, 일부 코드에서 강한 결합성을 보였다.
* TDD -> 클린 아키텍처 도입 고민
* [만들면서 배우는 클린 아키텍처 구입](https://ebook-product.kyobobook.co.kr/dig/epd/ebook/E000005295801)

---

#### 2024.02.04

* Member 도메인 리펙토링
* Member -> User로 도메인명 변경
* User명 과정 추가

## 참고 레퍼런스 URL

클린 아키텍처(패키지는 어떻게 하면 좋을까에 대한 참고)  
[Clean Architecture with Spring Boot: A good idea?](https://medium.com/@viniciusromualdobusiness/clean-architecture-with-spring-boot-a-good-idea-d6f97e450130)

헥사고날 아키텍처(Hexagonal Architecture) : 유연하고 확장 가능한 소프트웨어 디자인:  
https://tech.osci.kr/hexagonal-architecture/

[프로젝트에-새로운-아키텍처-적용하기](https://medium.com/naverfinancial/%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8%EC%97%90-%EC%83%88%EB%A1%9C%EC%9A%B4-%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0-99d70df6122b)
[Understanding Clean architecture with Example in Java](https://shivagoy.medium.com/overview-on-clean-architecture-a2715d982fdd)

## 참고 서적

[만들면서 배우는 클린 아키텍처 구입](https://ebook-product.kyobobook.co.kr/dig/epd/ebook/E000005295801)