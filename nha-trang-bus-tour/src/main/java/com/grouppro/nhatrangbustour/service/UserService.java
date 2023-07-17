package com.grouppro.nhatrangbustour.service;

import com.grouppro.nhatrangbustour.Entity.User;
import com.grouppro.nhatrangbustour.repository.UserRepository;
import com.grouppro.nhatrangbustour.service.interfaces.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Override
    public Long Register(User user) {
        if (user!=null){
            userRepository.save(user);
            return user.getUserId();
        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long uid) {
        return userRepository.getReferenceById(uid);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getUserByUserEmail(email);
    }
}
