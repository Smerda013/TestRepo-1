package com.greenfox.springadvanced.Security;

import com.greenfox.springadvanced.Service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService myUserDetailsService;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("aaa").password("aaa").roles("user")
                .and().withUser("bbb").password("bbb").roles("admin");

        auth.userDetailsService(myUserDetailsService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/movie/{id}").hasRole("admin")
                .antMatchers("/").permitAll()
                .and().formLogin();
    }

}
