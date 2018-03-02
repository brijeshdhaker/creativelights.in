/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.org.onclick.exceptions;

/**
 *
 * @author brijeshdhaker
 */
public class UserExistsException extends Exception {

    public UserExistsException() {
        super();
    }

    public UserExistsException(String s) {
        super(s);
    }

    public UserExistsException(String s, Throwable e) {
        super(s, e);
    }

}
