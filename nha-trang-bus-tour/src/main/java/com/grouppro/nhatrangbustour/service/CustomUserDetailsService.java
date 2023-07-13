package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.Entity.CustomUserDetails;
import com.grouppro.nhatrangbustour.Entity.User;
import com.grouppro.nhatrangbustour.repository.UserRepository;
import com.grouppro.nhatrangbustour.service.interfaces.ICustomUserDetailsService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Transactional
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements ICustomUserDetailsService,UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String role="";
        Set<GrantedAuthority> authoritySet =new HashSet<>();
        if (email.equals("nhatrangbus@gmail.com"))
        {
            role="Admin";
            authoritySet.add(new SimpleGrantedAuthority("ROLE_Admin"));
            User admin =new User();
            admin.setUserName("admin");
            admin.setUserEmail("nhatrangbus@gmail.com");
            return new CustomUserDetails(admin,authoritySet,role);
        }
        else {
            role="Customer";
            authoritySet.add(new SimpleGrantedAuthority("ROLE_Customer"));
            if (userRepository.getUserByUserEmail(email)==null){
                User user = new User();
                user.setUserEmail(email);
                user.setUserName("");
                user.setUserPhone("");
                userRepository.save(user);
            }
        }
        User customer = userRepository.getUserByUserEmail(email);
        return new CustomUserDetails(customer, authoritySet,role);
    }
}
