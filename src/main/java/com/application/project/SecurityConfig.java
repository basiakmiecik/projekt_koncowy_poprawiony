package com.application.project;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/", "/login**","/callback/", "/webjars/**", "/error**").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/loguj").permitAll()//hasAnyRole("ADMIN","USER")
                .antMatchers("/mypage").permitAll()//hasAnyRole("ADMIN","USER")
                .antMatchers("/dodajp").permitAll()//hasAnyRole("ADMIN","USER")
                .antMatchers("/person").permitAll()//hasAnyRole("ADMIN","USER")
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/style/**").permitAll()
                .antMatchers("/badlogin").permitAll()
                .antMatchers("/dodajuser").permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .csrf().ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from user where username = ?")
                .authoritiesByUsernameQuery("select username, role from user_role where username = ?");
    }
}
