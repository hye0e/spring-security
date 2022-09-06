### ExceptionTranslationFilter
- AuthenticationException
  - 인증 예외 처리
    1. AuthenticationEntryPoint 호출
       - 로그인 페이지 이동, 401 오류 코드 전달 등
    2. 인증 예외가 발생하기 전의 요청 정보를 저장
      - RequestCache - 사용자의 이전 요청 정보를 세션에 저장하고 이를 꺼내 오는 캐시 메카니즘
      - SavedRequest - 사용자가 요청했던 request 파라미터 값들, 그 당시의 헤더값들 등이 저장
- AccessDeniedException
  - 인가 예외 처리
  - AccessDeniedHandler에서 예외 처리하도록 제공

- http.exceptionHandling(): 예외처리 기능이 작동함

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter { // WebSecurityConfigurerAdapter deprecated

  @Autowired
  UserDetailsService userDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .formLogin() // 폼 로그인 인증방식 작동
            .successHandler(new AuthenticationSuccessHandler() {
              @Override
              public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                      Authentication authentication) throws IOException, ServletException {
                RequestCache requestCache = new HttpSessionRequestCache();
                SavedRequest savedRequest = requestCache.getRequest(request, response); // 원래 사용자가 가지고 있던 정보들을 가지고 있음
                String redirectUrl = savedRequest.getRedirectUrl(); // 사용자가 원래 가려고 했던 url을 가져올 수 있음
                response.sendRedirect(redirectUrl);
              }
            });

    http.exceptionHandling()
            .authenticationEntryPoint(new AuthenticationEntryPoint() { // 인증 실패 시 처리
              @Override
              public void commence(HttpServletRequest request, HttpServletResponse response,
                      AuthenticationException authException) throws IOException, ServletException {
                response.sendRedirect("/login"); // 스프링 시큐리티가 제공하는 로그인 페이지가 아님 
              }
            })
            .accessDeniedHandler(new AccessDeniedHandler() { // 인증 실패 시 처리
              @Override
              public void handle(HttpServletRequest request, HttpServletResponse response,
                      AccessDeniedException accessDeniedException) throws IOException, ServletException {
                response.sendRedirect("/denied");
              }
            });
  }
}
```
