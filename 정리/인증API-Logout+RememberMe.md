### Logout
- 세션 무효, 인증 토큰 삭제, 쿠키정보 삭제, 로그인 페이지로 리다이렉트
- http.logout(): 로그아웃 기능이 작동함
- 원칙적으로는 post 방식을 이용한다.

### Remember me 인증
- 세션이 만료되고 웹 브라우저가 종료도니 후에도 어플리케이이 사용자를 기억하는 기능
- 쿠키에 대한 http 요청을 확인한 후, 토큰 기반인증을 사용해 유효성을 검사하고 토큰이 검증되면 사용자는 로그인 됨
- 사용자 라이프 사이클
    1. 인증 성공(Remember-Me 쿠키 설정)
  2. 인증 실패(쿠키가 존재하면 쿠키 무효화)
  3. 로그아웃(쿠키가 존재하면 쿠키 무효화)
- http.rememberMe(): rememberMe 기능이 작동함

### Remember me 구현
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter { // WebSecurityConfigurerAdapter deprecated

  @Autowired
  UserDetailsService userDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .rememberMe() // rememberMe 기능 활성화
            .rememberMeParameter("remember-me") // remember 파라미터기능을 설정할수있다.
            .tokenValiditySeconds(3600) // default 는 14일이다.
            .userDetailsService(userDetailsService); // user 정보 조회하는 기능처리
  }
}
```

### Remember me 인증 필터
-  RememberMeAuthenticationFilter 가 작동 하는 시점
  1. 인증객체가 null 일 경우
2. 사용자가 remember-me 객체를 가지고 있는 경우

- token이 존재하면
  1. decode token(정상 유무 판단) -> 없다면 exception
  2. token 이 서로 일치하는가? -> 없다면 exception
  3. user 계정이 존재하는가? -> 없다면 exception
  4. 새로운 Authentication 생성
  5. AuthenticationManager 로 인증처리
- token이 존재하지않는다면
  1. chain.doFilter
