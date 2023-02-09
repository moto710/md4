package com.spbproductmanagementjwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CheckWithdrawBalance extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CheckWithdrawBalance(String message) {
        super(message);
    }
}
