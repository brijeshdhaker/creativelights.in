package com.org.onclick.exceptions;

/**
 *
 * @author brijeshdhaker
 */
public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String s) {
        super(s);
    }

    public UserNotFoundException(String s, Throwable e) {
        super(s, e);
    }
}
