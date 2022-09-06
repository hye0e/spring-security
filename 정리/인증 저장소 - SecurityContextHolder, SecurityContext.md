### SecurityContextHolder, SecurityContext.md
- SecurityContext 
  - SecurityContext 안에 > Authentication 안에 > user객체
  - Authentication 객체가 저장되는 보관소로 필요 시 언제든지 Authentication 객체를 꺼내어 쓸 수 있도록 제공되는 클래스
  - ThreadLocal 에 저장되어 아무 곳에서나 참조가 가능하도록 설계함
  - 인증이 완료되면 HttpSession에 저장되어 어플리케이션 전반에 걸쳐 전역적인 참조가 가능하다
  <br>
- SecurityContextHolder
  - SecurityContext를 감싸는 holder
  - SecurityContext 객체 저장 방식
    - MODE_THREADLOCAL: 스레드 당 SecurityContext 객체를 할당, 기본값
    - MODE_INHERITABLETHREADLOCAL: 메인 스레드와 자식 스레드에 관하여 동일한 SecurityContext를 유지
    - MODE_CLOBAL: 응용 프로그램에서 단 하나의 SecurityContext를 저장한다.
    - 예시:
  ```java
  import org.springframework.security.core.context.SecurityContextHolder;
  
  @Configuration
  @EnableWebSecurity
  public class SecurityConfig extends WebSecurityConfigurerAdapter { // Web
  
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
              .authorizeRequests()
              .anyRequest().authenticated(); // 모든 요청에 대해 보안요청을 하겠다.
      http
              .formLogin();
  
      SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }
  }
  ```
  <br>
- 확인해보기
  ```java
  @RestController
  public class SecurityController {
  
    @GetMapping("/")
    public String index(HttpSession session) {
      // 인증을 성공한 객체
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      // session을 이용하여 로그인한 객체를 꺼낼 수 있음
      SecurityContext context = (SecurityContext) session.getAttribute(
              HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
      Authentication authentication1 = context.getAuthentication();
  
      return "home";
    }
  
    @GetMapping("/thread")
    public String thread() {
      // 부모 스래드와 자식 스래드 간에 공유 안됨 -> threadLocal이 다르기 때문에
      new Thread(new Runnable() {
        @Override
        public void run() {
          // NULL -> 메인스레드에만 담았지 자식 스레드에 담은 적이 없기 때문에 null
          Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        }
      }).start();
  
      return "thread";
    }
  }
  ```
