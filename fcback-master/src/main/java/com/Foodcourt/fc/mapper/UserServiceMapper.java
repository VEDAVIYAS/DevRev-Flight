package com.Foodcourt.fc.mapper;

import com.Foodcourt.fc.Entity.User;
import com.Foodcourt.fc.dto.UserDetailsDTO;
import com.Foodcourt.fc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceMapper implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User users=userService.getUserByEmail(username);
        return new UserDetailsDTO(users);
    }
}
