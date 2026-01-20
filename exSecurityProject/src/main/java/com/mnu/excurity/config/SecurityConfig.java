package com.mnu.excurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.mnu.excurity.service.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final CustomLoginSuccessHandler customLoginSuccessHandler;
	private final CustomUserDetailsService customUserDetailsService;
	//id, 비번오류처리
	private final CustomLoginFailureHandler customLoginFailureHandler;
	@Bean
	SecurityFilterChain filerChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/User/**").authenticated()
        		//.requestMatchers("/User/**").hasAnyRole("USER")
                  // /User : 인증만 되면 들어갈 수 있는 주소
                .requestMatchers("/Manager/**").hasAnyRole("MANAGER", "ADMIN")
                  //MANAGER, ADMIN 을 가진 사용자만 /Manager 로 시작하는 경로애 접근 허용
                .requestMatchers("/Admin/**").hasAnyRole("ADMIN")
                    //ADMIN을 가진 사용자만 /Admin 로 시작하는 경로 접근 허용
                .anyRequest().permitAll()//다른 요청
               )
        	.userDetailsService(customUserDetailsService) 
          .formLogin(login->login
        		.loginPage("/Join/user_login")
               	.loginProcessingUrl("/Join/user_login") //login에 접속하면 시큐리티가 낚아채서 login 진행
               	.successHandler(customLoginSuccessHandler) //로그인 성공시
   				.usernameParameter("userid")
   				.passwordParameter("passwd")
   				.defaultSuccessUrl("/")
   				.failureHandler(customLoginFailureHandler) //로그인 실패시
          )
          .logout(logout->logout
        		  .logoutUrl("/User/user_logout")     // 로그아웃 요청 URL
        		  .logoutSuccessUrl("/")              // 로그아웃 후 이동
        		  .invalidateHttpSession(true)        // 세션 무효화 (기본 true)
        		  .clearAuthentication(true)          // 인증 정보 삭제
        		  .deleteCookies("JSESSIONID")        // 쿠키 삭제
          );

		return http.build();
	}
	

	//비번 암호화
   @Bean
   public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
   }
/*
   //비번암호화
   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder(); 
   }
*/
}
