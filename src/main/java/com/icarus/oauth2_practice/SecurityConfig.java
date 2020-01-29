package com.icarus.oauth2_practice;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final String authEndpoint = "https://accounts.google.com/o/oauth2/v2/auth?" +
            "access_type=offline" +
            "&scope=email profile" +
            "&access_type=offline" +
            "&redirect_uri=https%3A//localhost:8080/login/oauth2/code/google" +
            "&response_type=code" +
            "&client_id=172617982905-7909d5ec8d0el4kf6114jn21a4q1eu4j.apps.googleusercontent.com";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login()
                .loginPage("/login")
                .defaultSuccessUrl("/secret");
    }
}
