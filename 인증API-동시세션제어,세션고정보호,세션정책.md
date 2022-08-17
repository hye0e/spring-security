### 동시 세션 제어
1. 최대 세션 허용 개수를 초과했다고 가정
   1. 이전 사용자 세션 만료
   <br> 사용자2가 로그인을 시도할 때 사용자 1을 세션만료 설정
   2. 현재 사용자 인증 실패
   <br> 사용자1은 세션 성공을 시키고, 사용자2가 로그인 시 인증 예외 발생을 시켜 로그인을 실패

### 동시 세션 제어 구현
- http.sessionManagement(): 세션 관리 기능 작동
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter { // WebSecurityConfigurerAdapter deprecated

  @Autowired
  UserDetailsService userDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .sessionManagement() // 세션 관리 기능 활성화
            .maximumSessions(1) // 최대 허용 가능 세션 수, -1: 무제한 로그인 세션 허용
            .maxSessionsPreventsLogin(true) // 유형 선택 -> 동시 로그인 차단 false: 기존 세션 만료(위에서 1번째 경우)
            .invalidSessionUrl("/invalid") // (우선 시)세션이 유효하지 않을 때 이동 할 페이지
            .expiredUrl("/expired"); // 세션이 만료된 경우 이동할 페이지
  }
}
```

### 세션 고정 보호
- 세션 고정 공격: 공격자가 사용자의 쿠키를 알 경우, 로그인 없이 접속 가능하다
- 인증 할 때마다 새로운 세션과 쿠키가 생성되므로 공격자가 서버에 접속하더라도 세션아이디가 다르기 때문에 보호한다.

### 세션 고정 보호
- http.sessionManagement(): 세션 관리 기능이 작동함
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter { // WebSecurityConfigurerAdapter deprecated

   @Autowired
   UserDetailsService userDetailsService;

   @Override
   protected void configure(HttpSecurity http) throws Exception {
      http
              .sessionManagement() // 세션 관리 기능 활성화
              .sessionFixation() // 기본 값, none, migrateSession, newSession
              .changeSessionId();
   }
}
```

### 세션 정책
- SessionCreationPolicy.Always: 스프링 키슈리티가 항상 세션 생성
- SessionCreationPolicy.If_Required: 스프링 키슈리티가 필요 시 생성(기본 값)
- SessionCreationPolicy.Never: 스프링 시큐리티가 생성하지 않지만, 이미 존재하면 사용
- SessionCreationPolicy.Stateless: 스프링 시큐리티가 생성하지 않고 존재해도 사용하지 않음
<br> (JWT 토큰 방식을 이용할 경우엔, Stateless 정책을 이용해주어야 한다.)
