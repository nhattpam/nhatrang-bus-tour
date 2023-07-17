package com.grouppro.nhatrangbustour.service.interfaces;

import com.grouppro.nhatrangbustour.Entity.User;

import java.util.List;

public interface IUserService {
    Long Register(User user);
    List<User> getUsers();
    User getUserById(Long uid);
    User getUserByEmail(String email);
}
