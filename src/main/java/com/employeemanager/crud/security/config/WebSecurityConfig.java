package com.employeemanager.crud.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.employeemanager.crud.security.JWTAuthenticationEntryPoint;
import com.employeemanager.crud.security.auth.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

        @Autowired
        private JWTAuthenticationEntryPoint jwtAuthenticationEntryPoint;
        @Autowired
        private JWTAuthenticationFilter jwtAuthenticationFilter;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                                .requestMatchers(HttpMethod.GET, "/api/employee").hasAnyRole("ADMIN",
                                                "MANAGER")
                                .requestMatchers(HttpMethod.GET, "/api/employee/**").hasAnyRole("ADMIN",
                                                "MANAGER")
                                .requestMatchers(HttpMethod.POST, "/api/employee").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/api/employee").hasAnyRole("ADMIN",
                                                "MANAGER")
                                .requestMatchers(HttpMethod.DELETE, "/api/employee/**").hasAnyRole("ADMIN")
                                .requestMatchers("/users").hasAnyRole("ADMIN")
                                .requestMatchers("/roles").hasAnyRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                                .anyRequest().authenticated());

                // transfering exception control to JWTAuthenticationEntryPoint
                http.exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint));
                // stateless makes security to verify user every time
                http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
                // add JWT authentication filter
                http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

                // disabling csrf
                http.csrf(csrf -> csrf.disable());

                // using basic auth
                // http.httpBasic(Customizer.withDefaults());

                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
                        throws Exception {
                return authenticationConfiguration.getAuthenticationManager();
        }
}
