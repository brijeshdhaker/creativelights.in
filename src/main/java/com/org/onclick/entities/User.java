/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.onclick.entities;

/**
 *
 * @author brijeshdhaker
 */
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(unique = true, name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = true)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "enabled", nullable = true)
    private Boolean enabled;

    //@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Collection<Role> roles;

    /*
    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;
     */
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "add_ts")
    private Date addts = new Date();

    public User() {

    }

    public User(String name, String lastName, String email, String password, Collection<Role> roles) {

        this.firstName = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;

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

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getAddts() {
        return addts;
    }

    public void setAddts(Date addts) {
        this.addts = addts;
    }

    public String getRolesAsString() {
        String result = "";
        if (this.roles != null) {
            int i = 0;
            for (Role role : this.roles) {
                i++;
                result += role.getName();
                if (i != this.roles.size()) {
                    result += ",";
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String udetail = String.format("{ id:%d, name:'%s', email:'%s', password:'%s' roles:[%s] }", this.id, this.firstName, this.email, "XXXXXXX", "%s");
        String uroles = "";
        if (this.roles != null) {
            int i = 0;
            for (Role role : this.roles) {
                i++;
                uroles += String.format("{ id=%d, name='%s' }", role.getId(), role.getName());
                if (i != this.roles.size()) {
                    uroles += ",";
                }
            }
        }

        return String.format(udetail, uroles);
    }

}
