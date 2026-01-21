package com.mnu.excurity.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
//@Component 어노테이션을 붙이면 이 클래스를 자바 빈으로 등록시키라고 알려줄 수 있다. 
//해당 어노테이션이 명시되어 있으면 Spring이 자동으로 클래스의 인스턴스를 생성하고 
//종속성을 포함하여 각종 lifecycle을 관리해준다. 
//이를 통해 개발자는 오브젝트 생성 및 관리에 대한 걱정보다 비즈니스 로직 자체에 집중할 수 있다고 한다.
@Component
public class CustomLoginSuccessHandler
        implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
            			HttpServletRequest request,
            			HttpServletResponse response,
            				Authentication authentication) throws IOException {

        HttpSession session = request.getSession();

        session.setAttribute("user", authentication.getPrincipal());
        session.setMaxInactiveInterval(1800);

        System.out.println("로그인 성공 핸들러 진입");
        System.out.println("사용자: " + authentication.getName());
        response.sendRedirect("/");
    }
}
