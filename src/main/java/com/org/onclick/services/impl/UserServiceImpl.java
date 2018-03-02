package com.org.onclick.services.impl;

import com.org.onclick.entities.Role;
import com.org.onclick.entities.User;
import com.org.onclick.exceptions.UserExistsException;
import com.org.onclick.repository.UserRepository;
import com.org.onclick.services.UserService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final AtomicInteger counter = new AtomicInteger();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        List<User> userRecords = new ArrayList<>();
        userRepository.findAll().forEach(userRecords::add);
        return userRecords;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public void addUser(User user) throws UserExistsException {
        if (exists(user)) {
            throw new UserExistsException("There is an account already exists with email adress - " + user.getEmail());
        }
        if (null != user.getPassword()) {
            String password = user.getPassword();
            user.setPassword(passwordEncoder.encode(password));
        }
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) throws UsernameNotFoundException {
        try {
            User user = userRepository.findOne(id);
            if (user == null) {
                throw new UsernameNotFoundException("User not exists in system.");
            }
            userRepository.delete(id);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not exists in system.");
        }
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles())
        );
    }

    @Override
    public boolean exists(User user) {
        User expectedUser = userRepository.findByEmail(user.getEmail());
        return (expectedUser != null);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(
                role -> new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());
    }

    @Override
    public void update(User user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
