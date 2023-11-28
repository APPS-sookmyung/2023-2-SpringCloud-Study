package com.example.userservice.security;

import com.example.userservice.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private Environment env;
    public WebSecurity(Environment env,UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.env=env;
        this.userService=userService;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/users/**").permitAll();
        http.authorizeRequests().antMatchers("/actuator/**").permitAll();
        http.authorizeRequests().antMatchers("/health_check/**").permitAll();
        http.authorizeRequests().antMatchers("/**") //모든 코드에 대해서 통과시키지 않음 (not permitAll)
                .hasIpAddress("192.168.0.4")//내 ip 로 제한
                .and()
                .addFilter(getAuthenticationFilter()); //인증필터를 거친것만
        http.headers().frameOptions().disable();
    }

    private AuthenticationFilter getAuthenticationFilter() throws Exception{
        AuthenticationFilter authenticationFilter= new AuthenticationFilter(authenticationManager(),userService,env);
        //authenticationFilter.setAuthenticationManager(authenticationManager()); //위에서 했으므로 따로 authenticationManager 호출할 필요 없음

        return authenticationFilter;
    }

    //select pwd from users where email=?
    //db에 있는 pwd(encrypted) == input pwd(encrypted)를 비교해야함
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { //인증에 관련된 작업
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder); //변환 작업
    }
}
