### 의존성 추가
```
    <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
```
루트 경로를 포함한 모든 인증을 받아야 접근가능 하도록 변경 됩니다.
<br>
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

### 사용자 정의 보안 기능 구현
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter { // WebSecurityConfigurerAdapter deprecated
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests()
            .anyRequest().authenticated(); // 모든 요청에 대해 보안요청을 하겠다.
    http
            .formLogin() // 폼 로그인 인증방식 작동
            .loginPage("/loginPage") // 로그인 페이지 설정
            .defaultSuccessUrl("/") // 로그인 성공 시 리다이렉트 시킬 url
            .failureUrl("/login_proc") // 로그인 실패 시 리다이렉트 시킬 url
            .successHandler(new AuthenticationSuccessHandler() {
              @Override
              public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                      Authentication authentication) throws IOException, ServletException {
                System.out.println("authentication " + authentication.getName()); // 인증 성공한 username
                response.sendRedirect("/");
              }
            })
            .failureHandler(new AuthenticationFailureHandler() {
              @Override
              public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                      AuthenticationException exception) throws IOException, ServletException {
                System.out.println("exception " + exception.getMessage());
                response.sendRedirect("/login");
              }
            })
            .permitAll(); // /loginPage 에 인증이 없어도 누구나 이 경로로는 접근이 가능하도록 설정
  }
}

```
application.properties 에 name과 password 설정
```
spring.sercurity.user.name = user
spring.sercurity.user.password = ****
```
### form 인증 방식
1. GET /home 요청
2. 인증이 안되면 로그인 페이지로 리다이렉트
3. session 및 인증 토큰 생성/저장
4. 세션에 저장된 인증 토큰으로 접근/인증유지

http.formLogin: form 로그인인증기능이 작동됨

