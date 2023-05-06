package com.Foodcourt.fc.service;

import com.Foodcourt.fc.Entity.User;
import com.Foodcourt.fc.Repository.UserRepository;
import com.Foodcourt.fc.dto.UserDetailsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository usersRepository;


    public User getUserByEmail(String email) {
        return usersRepository.findByEmail(email);
    }

    public User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsDTO userDetailsDTO = (UserDetailsDTO) authentication.getPrincipal();
        return usersRepository.findByEmail(userDetailsDTO.getEmail());
    }
}
