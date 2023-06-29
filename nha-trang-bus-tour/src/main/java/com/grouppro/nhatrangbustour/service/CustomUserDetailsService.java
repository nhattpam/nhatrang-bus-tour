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
        if (userRepository.getUserByUserEmail(email)==null){
            throw new UsernameNotFoundException(email);
        }
        User customer = userRepository.getUserByUserEmail(email);
        String role="";
        Set<GrantedAuthority> authoritySet =new HashSet<>();
        if (email.equals("nhatrangbus@gmail.com"))
        {
            role="Admin";
            authoritySet.add(new SimpleGrantedAuthority("ROLE_Admin"));
        }
        else {
            role="Customer";
            authoritySet.add(new SimpleGrantedAuthority("ROLE_Customer"));
        }
        return new CustomUserDetails(customer, authoritySet,role);
    }
}
