### 의존성 추가
```
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
```
루트 경로를 포함한 모든 인증을 받아야 접근가능 하도록 변경이 됨
default id: user
password: Using generated security password: ~~~

### 의존성 추가 시 일어나는 일들
- 서버가 기동되면 스프링 시큐리티의 초기화 작업 및 보안 설정이 이루어진다.
- 별도의 설정이나 구현을 하지않아도 기본적인 웹 보안 기능이 현재 시스템에 연동되어 작동된다.
  1. 모든 요청은 인증이 되어야 자원에 접근이 가능하다
  2. 인증 방식은 폼 로그인 방식과 httpBasic 로그인 방식을 제공한다
  3. 기본 로그인 페이지를 제공한다.
  4. 기본 계정 한 개를 제공한다. 
- 문제점
  1. 계정 추가, 권한 추가, DB 연동 등
  2. 기본적인 보안 기능 외에 시스템에서 필요로 하는 더 세부적이고 추가적인 보안기능이 필요하다.
  
### 인증 API - 사용자 정의 보안 기능 구현
- WebSecurityConfigurerAdapter
: 스프링 시큐리티의 웹 보안 기능 초기화 및 설정
- HttpSecurity
: 세부적인 보안 기능을 설정할 수 있는 API 제공
위에 두가지 클래스가 기본적인 웹 보안 기능을 설정할 수 있는 클래스이다. 

