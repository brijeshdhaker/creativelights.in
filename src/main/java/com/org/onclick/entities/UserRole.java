package com.org.onclick.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author brijeshdhaker
 */
@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "rolenm")
    private String rolenm;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "add_ts")
    private Date addts = new Date();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User user;

    public UserRole() {

    }

    public UserRole(String rolenm) {
        this.rolenm = rolenm;
    }

    public UserRole(String rolenm, User user) {
        this.rolenm = rolenm;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRolenm() {
        return rolenm;
    }

    public void setRolenm(String rolenm) {
        this.rolenm = rolenm;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getAddts() {
        return addts;
    }

    public void setAddts(Date addts) {
        this.addts = addts;
    }

}
