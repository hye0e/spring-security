package io.security.basicsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter { // WebSecurityConfigurerAdapter deprecated

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .anyRequest().authenticated(); // 모든 요청에 대해 보안요청을 하겠다.
        http
            .formLogin(); // 폼 로그인 인증방식 작동
//            .loginPage("/loginPage") // 로그인 페이지 설정
//            .defaultSuccessUrl("/") // 로그인 성공 시 리다이렉트 시킬 url
//            .failureUrl("/login_proc") // 로그인 실패 시 리다이렉트 시킬 url
//            .successHandler(new AuthenticationSuccessHandler() {
//                @Override
//                public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                    Authentication authentication) throws IOException, ServletException {
//                    System.out.println("authentication " + authentication.getName()); // 인증 성공한 username
//                        response.sendRedirect("/");
//                }
//            })
//            .failureHandler(new AuthenticationFailureHandler() {
//                @Override
//                public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//                    AuthenticationException exception) throws IOException, ServletException {
//                    System.out.println("exception " + exception.getMessage());
//                    response.sendRedirect("/login");
//                }
//            })
//            .permitAll(); // /loginPage 에 인증이 없어도 누구나 이 경로로는 접근이 가능하도록 설정
//        http
//            .logout()
//            .logoutUrl("/logout")
//            .logoutSuccessUrl("/login")
//            .addLogoutHandler(new LogoutHandler() {
//                @Override
//                public void logout(HttpServletRequest request, HttpServletResponse response,
//                    Authentication authentication) {
//                    HttpSession session = request.getSession();
//                    session.invalidate();
//                }
//            })
//            .logoutSuccessHandler(new LogoutSuccessHandler() {
//                @Override
//                public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
//                    Authentication authentication) throws IOException, ServletException {
//                    System.out.println("logout");
//                    response.sendRedirect("/login");
//                }
//            }) // 그 다음 동작을 할 수 있도록 handler
//            .deleteCookies("remember-me") // 로그아웃 할 때 삭제하고 싶은 쿠키 이름
//            .and()
//            .rememberMe() // rememberMe 기능 활성화
//            .rememberMeParameter("remember-me") // remember 파라미터기능을 설정할수있다.
//            .tokenValiditySeconds(3600) // default 는 14일이다.
//            .userDetailsService(userDetailsService) // user 정보 조회하는 기능처리
//        ;


        http
            .sessionManagement()
            .maximumSessions(1)
            .maxSessionsPreventsLogin(true);
    }
}
