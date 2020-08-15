package com.algo.inc.config.security;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Log
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BoardUserDetailsService boardUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("security configure method");
        http.authorizeRequests()
                .antMatchers("/", "/system/**").permitAll()
                .antMatchers("/board/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN");

        http.csrf().disable();
        http.formLogin().loginPage("/system/login").defaultSuccessUrl("/view/board/getBoardList", true);
        http.exceptionHandling().accessDeniedPage("/system/accessDenied");
        http.logout().logoutUrl("/system/logout").invalidateHttpSession(true).logoutSuccessUrl("/");
        http.userDetailsService(boardUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
