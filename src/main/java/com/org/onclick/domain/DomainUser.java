package com.org.onclick.domain;

import java.io.Serializable;

public class DomainUser implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean enabled;
    private Boolean isAdmin;
    private Boolean isUser;
    private Boolean isManager;
    private Boolean isSuperUser;

    public DomainUser(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getIsUser() {
        return isUser;
    }

    public void setIsUser(Boolean isUser) {
        this.isUser = isUser;
    }

    public Boolean getIsManager() {
        return isManager;
    }

    public void setIsManager(Boolean isManager) {
        this.isManager = isManager;
    }

    public Boolean getIsSuperUser() {
        return isSuperUser;
    }

    public void setIsSuperUser(Boolean isSuperUser) {
        this.isSuperUser = isSuperUser;
    }

}
