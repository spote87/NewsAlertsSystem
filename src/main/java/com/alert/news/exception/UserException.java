package com.alert.news.exception;

import lombok.Getter;

/**
 * @author Shivaji Pote
 */
@Getter
public class UserException extends Exception {

    /**
     * serial version id
     */
    private static final long serialVersionUID = 1L;

    private final String message;


    /**
     * @param message exception message
     */
    public UserException(final String message) {
        super(message);
        this.message = message;
    }

}
