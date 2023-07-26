package com.vti.testing.config;

import com.vti.testing.config.exception.AuthExceptionHandler;
import com.vti.testing.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private IAccountService accountService; 

    @Autowired
    private AuthExceptionHandler authExceptionHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authExceptionHandler)
                .accessDeniedHandler(authExceptionHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/departments/**").hasAnyAuthority("ADMIN", "MANAGER")
                .antMatchers("/api/v1/accounts/**").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }
}
