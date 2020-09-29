package com.springsecurity.demo.services.serviceimpl;

import com.springsecurity.demo.exceptions.NotFoundException;
import com.springsecurity.demo.models.User;
import com.springsecurity.demo.repositories.UserRepository;
import com.springsecurity.demo.services.UserService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Primary
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
            return fetchUser(username);
    }

    @Override
    public User fetchUser(Long id) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) return user.get();
        throw new NotFoundException("User doesn't exist!!");
    }

    @Override
    public User fetchUser(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElse(null);
    }

    @Override
    public List<User> fetchAllUsers() {
        return userRepository.findAll();
    }
}
