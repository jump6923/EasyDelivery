package com.sparta.easydelivery.security.config;

import com.sparta.easydelivery.security.ExceptionHandleFilter;
import com.sparta.easydelivery.security.jwt.JwtAuthorizationFilter;
import com.sparta.easydelivery.security.jwt.JwtUtil;
import com.sparta.easydelivery.user.implement.UserDetailsServiceImpl;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter(){
        return new JwtAuthorizationFilter(jwtUtil, userDetailsService);
    }

    @Bean
    public ExceptionHandleFilter ExceptionHandleFilter() {
        return new ExceptionHandleFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf((csrf) -> csrf.disable());

        httpSecurity.sessionManagement((sessionManageMent) ->
                sessionManageMent.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        httpSecurity.authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // resources 접근 허용 설정
                        .requestMatchers("/api/users/**").permitAll() // '/api/users/'로 시작하는 요청 모두 접근 허가
                        .requestMatchers(HttpMethod.GET,"/api/products/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/reviews/**").permitAll()
                        .anyRequest().authenticated() // 그 외 모든 요청 인증처리
        );

        httpSecurity.addFilterBefore(jwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterBefore(ExceptionHandleFilter(), JwtAuthorizationFilter.class);

        return httpSecurity.build();
    }
}