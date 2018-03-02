/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.onclick.repository;

import com.org.onclick.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author brijeshdhaker
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByFirstName(String firstName);
    public User findByEmail(String email);
    
}
