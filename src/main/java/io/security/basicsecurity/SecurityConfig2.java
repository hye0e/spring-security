package io.security.basicsecurity;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig2 extends WebSecurityConfigurerAdapter { // WebSecurityConfigurerAdapter deprecated
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/admin/**") // 특정한 url
            .authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .httpBasic();
    }
}

@Configuration
class SecurityConfig2_2 extends WebSecurityConfigurerAdapter {
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() // url 지정 없음
            .anyRequest().permitAll()
            .and()
            .formLogin();
    }
}
