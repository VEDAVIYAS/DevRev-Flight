package com.Foodcourt.fc.mapper;

import com.Foodcourt.fc.Entity.User;
import com.Foodcourt.fc.dto.LoginDTO;
import com.Foodcourt.fc.service.UserService;
import com.Foodcourt.fc.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class LoginMapper {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserServiceMapper userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;
    public String createAuthenticationToken(LoginDTO loginDTO, HttpServletResponse response) {

        try
        {
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(loginDTO.getEmail());
            User user = userService.getUserByEmail(loginDTO.getEmail());
            try {
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
                );
            }
            catch (Exception e){
                return "Invalid password";
            }
            String jwt = jwtTokenUtil.generateToken(userDetails.getUsername());
            return jwt;
        }
        catch(UsernameNotFoundException e)
        {
            throw new UsernameNotFoundException("Username not found");
        }
    }

}
