package io.security.basicsecurity;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

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
