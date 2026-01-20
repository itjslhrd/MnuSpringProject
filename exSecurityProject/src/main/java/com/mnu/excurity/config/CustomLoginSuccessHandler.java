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
