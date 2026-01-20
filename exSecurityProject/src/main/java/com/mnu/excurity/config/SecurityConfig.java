package com.mnu.excurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(auth->auth
				.requestMatchers("/Admin/**").hasRole("Admin")
				.requestMatchers("/User/**").hasRole("User")
				.anyRequest().permitAll()//누구나
				)
				//.csrf().disable()
				.formLogin(login->login
						.loginPage("/Join/user_login")
						.defaultSuccessUrl("/")//로그인 성공시 이동 페이지
						)
				.logout(logout->logout
						.logoutUrl("/User/logout")
						.logoutSuccessUrl("/")//로그아웃 시 이동 페이지
						)
				.build();
	}
	
	//비번암호화
   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder(); 
   }

   /*
	//비번 암호화
   @Bean
   public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
   }
   */

}
