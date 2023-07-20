package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.Entity.CustomUserDetails;
import com.grouppro.nhatrangbustour.Entity.User;
import com.grouppro.nhatrangbustour.config.JsonFileReader;
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
import java.util.Iterator;
import java.util.List;
import java.util.Set;


@Transactional
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements ICustomUserDetailsService,UserDetailsService {
    private final UserRepository userRepository;
    private final JsonFileReader jsonFileReader;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String role="";
        Set<GrantedAuthority> authoritySet =new HashSet<>();
//        List<String> adminEmails=jsonFileReader.getAdminEmails();
        //Iterator iterator=adminEmails.iterator();
        //System.out.println(adminEmails.size());
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }
        if (email.equals("nhatrangbus@gmail.com"))
        {
            role="Admin";
            authoritySet.add(new SimpleGrantedAuthority("ROLE_Admin"));
            User admin =new User();
            admin.setUserName("admin");
            admin.setUserEmail(email);
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
