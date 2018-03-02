/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.onclick.services;

import com.org.onclick.entities.User;
import com.org.onclick.exceptions.UserExistsException;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author brijeshdhaker
 */
public interface UserService extends UserDetailsService {

    void addUser(User user) throws UserExistsException;

    void delete(Long id);

    List<User> getAllUsers();

    User getUserById(Long id);

    User getUserByEmail(String email);

    void update(User user);

    boolean exists(User user);
}
