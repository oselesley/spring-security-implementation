package com.springsecurity.demo.services;

import com.springsecurity.demo.exceptions.NotFoundException;
import com.springsecurity.demo.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    User fetchUser(Long id) throws NotFoundException;
    User fetchUser(String email) throws NotFoundException;
    List<User> fetchAllUsers();
}
