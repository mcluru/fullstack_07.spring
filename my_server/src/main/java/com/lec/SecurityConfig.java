package com.lec;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/login_member").permitAll() // 회원 로그인 페이지 경로
//                .antMatchers("/login_admin").permitAll() // 관리자 로그인 페이지 경로
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/#") // 사용자 정의 로그인 페이지 경로
//                .permitAll();
//    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//		        .antMatchers("/admin/**").authenticated() // "/admin/**" 패턴에 대해 인증 요구
		        .antMatchers("/**").permitAll() // 나머지 URL은 인증 없이 접근 허용
                .anyRequest().authenticated()
                .and()
                .csrf().disable(); // CSRF 보안 기능 비활성화
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/**"); // 정적 리소스 접근 허용
    }
   
}
