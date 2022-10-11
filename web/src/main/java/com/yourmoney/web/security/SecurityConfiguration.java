package com.yourmoney.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration  {

    private final UserDetailsServiceImpl userDetailsService;
    private final Environment environment;

    public SecurityConfiguration(UserDetailsServiceImpl userDetailsService, Environment environment) {
        this.userDetailsService = userDetailsService;
        this.environment = environment;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {

        http.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**/api/v1/users/**").permitAll()
                .antMatchers("/**/api/**").hasRole("ADMIN")
                .and()
                .addFilter(getJWTAuthenticationFilter(authenticationManager))
                .addFilter(new JWTAuthorizationFilter(authenticationManager, userDetailsService, environment))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }


    public JWTAuthenticationFilter getJWTAuthenticationFilter(  AuthenticationManager authenticationManager) {
        var jwtAuthenticationFilter = new JWTAuthenticationFilter(authenticationManager, environment);
        jwtAuthenticationFilter.setFilterProcessesUrl("/api/v1/users/login");

        return jwtAuthenticationFilter;
    }

    @Bean
    AuthenticationManager authenticationManager(UserDetailsService userDetailsService, BCryptPasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider::authenticate;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}