/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.onclick.domain;

import com.org.onclick.entities.User;
import com.org.onclick.entities.Role;
import java.util.Collection;

import org.springframework.security.core.authority.AuthorityUtils;

/**
 *
 *
 * @author brijeshdhaker
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getFirstName(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRolesAsString()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Collection<Role> getRole() {
        return user.getRoles();
    }

}
