package com.algo.inc.config.security;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
                .antMatchers("/").permitAll()
                .antMatchers("/member/**").authenticated()
                .antMatchers("/manager/**").hasRole("MANAGER")
                .antMatchers("/admin/**").hasRole("ADMIN");

        http.csrf().disable();
        http.formLogin().loginPage("/login").defaultSuccessUrl("/loginSuccess", true);
        http.exceptionHandling().accessDeniedPage("/accessDenied");
        http.logout().invalidateHttpSession(true).logoutSuccessUrl("/login");
        http.userDetailsService(boardUserDetailsService);
    }

    @Autowired
    public void authenticate(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.inMemoryAuthentication()
                .withUser("manager")
                .password("{noop}1111")
                .roles("MANAGER");

        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}2222")
                .roles("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
