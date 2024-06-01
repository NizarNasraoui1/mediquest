package com.qonsult.config.security;

import com.qonsult.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration @EnableWebSecurity @RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    private final RequestInterceptor requestInterceptor;
    private final ApplicationContext context;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean(), context);
//        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/api/mediquest/auth/login/**", "/api/mediquest/public/auth/refresh-token","/swagger-ui/**","/swagger-ui.html/**","/api-docs/**","/actuator/**","/api/mediquest/public/**").permitAll();
//        http.authorizeRequests().antMatchers(GET, "/api/user/**").hasAnyAuthority("ROLE_USER");
//        http.authorizeRequests().antMatchers(POST, "/api/user/save/**").hasAnyAuthority("ROLE_ADMIN");
//        http.authorizeRequests().antMatchers(GET, "/api/**").hasAnyAuthority("ADMIN");
//        http.authorizeRequests().antMatchers(POST, "/api/**").hasAnyAuthority("ADMIN");
//        http.authorizeRequests().antMatchers(PUT, "/api/**").hasAnyAuthority("ADMIN");
//        http.authorizeRequests().antMatchers(DELETE, "/api/**").hasAnyAuthority("ADMIN");
        http.authorizeRequests().anyRequest().authenticated();
        //http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
//        http.addFilterBefore(requestInterceptor, CustomAuthorizationFilter.class);

    }
}
