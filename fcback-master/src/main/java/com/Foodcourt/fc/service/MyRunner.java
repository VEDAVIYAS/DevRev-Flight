package com.Foodcourt.fc.service;

import com.Foodcourt.fc.Entity.Roles;
import com.Foodcourt.fc.Entity.User;
import com.Foodcourt.fc.Repository.RoleRepository;
import com.Foodcourt.fc.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyRunner implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
//        List<Roles> roles=new ArrayList<>();
//        Roles roles1=new Roles("ADMIN");
//        //roles1.setUser(new ArrayList<>());
//        roleRepository.save(roles1);
//        roles.add(roles1);
//        userRepository.save(new User("santhosha.20cse@kongu.edu",passwordEncoder.encode("foodie"),null,roles));

    }

}
