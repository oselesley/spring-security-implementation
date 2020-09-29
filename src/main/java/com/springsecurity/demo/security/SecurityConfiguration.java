package com.springsecurity.demo.security;

import com.springsecurity.demo.filters.JWTRequestFilter;
import com.springsecurity.demo.services.UserService;
import com.springsecurity.demo.services.serviceimpl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

import static com.springsecurity.demo.security.Permission.*;
import static com.springsecurity.demo.security.Role.*;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private PasswordEncoder encoder;
    private DataSource dataSource;
    private UserService userService;
    private JWTRequestFilter jwtRequestFilter;

    public SecurityConfiguration(PasswordEncoder encoder, DataSource dataSource, UserService userService, JWTRequestFilter jwtRequestFilter) {
        this.encoder = encoder;
        this.dataSource = dataSource;
        this.userService = userService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
               .csrf().disable()
               .authorizeRequests()
               .antMatchers("/authenticate/", "/h2/**", "/").permitAll()
//               .antMatchers("/api/v1/**").hasRole(STUDENT.name())
               .antMatchers(HttpMethod.POST, "/api/v1/students/**").hasAuthority("user:write")
//               .antMatchers(HttpMethod.PUT, "/api/v1/students/**").hasAuthority(COURSE_WRITE.getPermission())
//               .antMatchers(HttpMethod.DELETE, "/api/v1/students/**").hasAuthority(STUDENT_WRITE.getPermission())
//               .antMatchers(HttpMethod.GET, "/api/v1/students/**").hasAnyRole(ADMIN.name(), ADMIN_TRAINEE.name())
               .antMatchers(HttpMethod.GET,"/api/v1/students/**").hasAnyRole("USER", "ADMIN")
               .antMatchers(HttpMethod.GET, "/api/v1/users/**").hasRole("ADMIN")
               .anyRequest()
               .authenticated();

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("ose")
//                .password(encoder.encode("lesley"))
//                .roles("ADMIN")
//                .and()
//                .withUser("davido")
//                .password(encoder.encode("fem"))
//                .roles("STUDENT");
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

//    @Bean
//    public UserDetailsService userDetails() {
//        UserDetails ose = User.builder()
//                .username("oselesley")
//                .password(encoder.encode("password"))
////                .roles(STUDENT.name())
//                .authorities(STUDENT.getGrantedAuthorities())
//                .build();
//
//        UserDetails anna = User.builder()
//                .username("annabell")
//                .password(encoder.encode("password234"))
////                .roles(ADMIN.name())
//                .authorities(ADMIN.getGrantedAuthorities())
//                .build();
//
//        UserDetails tom = User.builder()
//                .username("tom")
//                .password(encoder.encode("password12"))
////                .roles(ADMIN.name())
//                .authorities(ADMIN_TRAINEE.getGrantedAuthorities())
//                .build();
//        return new InMemoryUserDetailsManager(ose, anna, tom);
//    }
}
