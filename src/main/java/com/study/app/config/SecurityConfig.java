package com.study.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        /* HttpSecurity: 세부적인 보안 기능을 설정할 수 있는 api 를 제공.
                        - URL 접근 권한 설정
                        - 커스텀 로그인 페이지 지원
                        - 인증 후 성공/실패 핸들링
                        - 사용자 로그인/로그아웃itAll())
                .formLogin()
                - CSRF 공격으로부터 보호
        */

        return http // 제한 해제하는 부분
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .formLogin()
                .and().build();



        /** formLogin() 이후 사용할 수 있는 메서드
         http.formLogin()
         .loginPage("/login.html")       // 커스텀 로그인 페이지 보여줄때
         .defaultSuccessUrl("/index")    // 로그인 성공 후 이동할 페이지 경로
         .failureUrl("/login.html?error=true") // 로그인 실패 후 이동할 페이지
         .usernameParameter("유저네임")      // 아이디 파라미터명 설정

         */
    }
}
