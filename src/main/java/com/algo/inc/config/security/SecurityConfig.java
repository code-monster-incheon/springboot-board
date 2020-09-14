package com.algo.inc.config.security;

import com.algo.inc.domain.member.Role;
import com.google.common.collect.Lists;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;

@Log
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final CustomUserService customUserService;

    @Autowired
    public SecurityConfig(DataSource dataSource, CustomUserService customUserService)
    {
        this.dataSource = dataSource;
        this.customUserService = customUserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
            .antMatchers("/view/board/detail/**").permitAll()
            .antMatchers("/view/board/register/**").hasAnyRole("ADMIN", "MANAGER", "GUEST")
            .antMatchers("/view/board/modify/**").hasAnyRole("ADMIN", "MANAGER", "GUEST")
            .antMatchers("/admin/**").hasRole(Role.ADMIM.getRoleName());

        http.exceptionHandling().accessDeniedPage("/accessDenied");
        http.formLogin().loginPage("/system/login").defaultSuccessUrl("/", true);
        http.logout().logoutUrl("/system/logout").invalidateHttpSession(true);

        http.rememberMe()
            .key("ysh")
            .userDetailsService(customUserService)
            .tokenRepository(getJDBCRepository())
            .tokenValiditySeconds(60 * 60 * 24);
    }

    private JdbcTokenRepositoryImpl getJDBCRepository()
    {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(customUserService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

}
