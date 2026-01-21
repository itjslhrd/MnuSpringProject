package com.mnu.excurity.config;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
//@Component 어노테이션을 붙이면 이 클래스를 자바 빈으로 등록시키라고 알려줄 수 있다. 
//해당 어노테이션이 명시되어 있으면 Spring이 자동으로 클래스의 인스턴스를 생성하고 
//종속성을 포함하여 각종 lifecycle을 관리해준다. 
//이를 통해 개발자는 오브젝트 생성 및 관리에 대한 걱정보다 비즈니스 로직 자체에 집중할 수 있다고 한다.
public class CustomLoginFailureHandler
        implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception)
            throws IOException, ServletException {

        String errorMessage;
        if (exception instanceof UsernameNotFoundException) {
            errorMessage = "존재하지 않는 아이디입니다.";
        } else if (exception instanceof BadCredentialsException) {
            errorMessage = "비밀번호가 일치하지 않습니다.";
        } else {
            errorMessage = "로그인에 실패했습니다.";
        }

        response.sendRedirect(
                "/Join/user_login?errorMessage=" +
                URLEncoder.encode(errorMessage, "UTF-8")
        );
    }
}