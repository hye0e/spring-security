package io.security.basicsecurity;

import javax.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/")
    public String index(HttpSession session) {
        // 인증을 성공한 객체
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // session을 이용하여 로그인한 객체를 꺼낼 수 있음
        SecurityContext context = (SecurityContext)session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
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
    @GetMapping("loginPage")
    public String loginPage() {
        return "loginPage";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin/pay")
    public String adminPay() {
        return "adminPay";
    }

    @GetMapping("/admin/**")
    public String admin() {
        return "admin";
    }

    @GetMapping("login")
    public String login() {
        return "admin";
    }
}
