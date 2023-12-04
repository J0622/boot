package com.docmall.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security 사용을 위한 Configuration Class를 작성하기 위해서
 * WebSecurityConfigurerAdapter를 상속하여 클래스를 생성하고
 * @Configuration 애노테이션 대신 @EnableWebSecurity 애노테이션을 추가한다.
 */

@EnableWebSecurity
//@Configuration 버전이 업데이트 되면서 해당클래스는 위 어노테이션으로 사용하도록 변경됐음
public class SecurityConfig {
	
	@Bean
   public BCryptPasswordEncoder bCryptPasswordEncoder() {
	   return new BCryptPasswordEncoder();
   }
   
//	스프링 시큐리티 설정, 아래 설정을 안하면 시큐리티에서 제공하는 로그인 페이지가 동작한다.
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
			.csrf().disable();
		
		return http.build();
	}
}
