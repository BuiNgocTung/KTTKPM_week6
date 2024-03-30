package com.example.service;

import com.example.authen.UserPrincipal;
import com.example.entity.User;

public interface UserService {
    User createUser(User user);
    UserPrincipal findByUsername(String username);
}
