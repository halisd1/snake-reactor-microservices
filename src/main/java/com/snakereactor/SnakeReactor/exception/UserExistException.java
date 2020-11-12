package com.snakereactor.SnakeReactor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserExistException extends Exception {

    private static final long serialVersionUID = 1L;

    public UserExistException(String errorMessage){
        super(errorMessage);
    }
}
