package com.employeemanager.crud.security.auth;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.employeemanager.crud.security.JWTHelper;
import com.employeemanager.crud.security.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTHelper jwtHelper;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String requestHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;

        if (requestHeader != null && requestHeader.startsWith("Bearer")) {
            // extract token from request header
            token = requestHeader.substring(7);
            try {
                username = this.jwtHelper.fetchUserName(token);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid Header Value !! ");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // fetch user detail from username
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            Boolean validateToken = this.jwtHelper.validateToken(token, userDetails);

            if (validateToken) {
                // set the authentication
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                // add Request Details to authentication
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // Set the authentication
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                System.out.println("Invalid Token");
            }
        }
        // pass further filteration to Spring Security
        filterChain.doFilter(request, response);
    }

}
