package com.Foodcourt.fc.config;

import com.Foodcourt.fc.mapper.UserServiceMapper;
import com.Foodcourt.fc.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    UserServiceMapper userdetailservice;
    @Autowired
    JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationHeader = (request).getHeader("Authorization");

        String username = null;
        String jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtil.extractUsername(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            try
            {
                UserDetails userDetails = this.userdetailservice.loadUserByUsername(username);
                if (jwtUtil.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            catch (BadCredentialsException e)
            {
                System.out.println("wrong pass");
            }
            catch(UsernameNotFoundException e)
            {
                throw new UsernameNotFoundException("Username not found");
            }
            catch(ExpiredJwtException e)
            {
                //throw new TourApiException("TA2", HttpStatus.REQUEST_TIMEOUT);
            }
        }
//        else{
//            try {
//                throw new Exception("User not logged in");
//            } catch (Exception e) {
//                throw new RuntimeException("User not logged in");
//            }
//        }
        if(jwtUtil.legitJWT(jwt)) {
            handleInvalidJWTRequest(response);
            return;
        }
        filterChain.doFilter(request, response);
    }
    private void handleInvalidJWTRequest(HttpServletResponse response) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        ExceptionResponseHandler errorResponse = new ExceptionResponseHandler();
//        errorResponse.setErrorMessgae("JWT logged out");
//        errorResponse.setErrorCode("TA10");
//        errorResponse.setStatus(403);
//        response.setContentType("application/json");
//        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
//        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
