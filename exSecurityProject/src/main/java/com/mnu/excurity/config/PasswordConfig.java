package com.mnu.excurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class PasswordConfig {
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
