package com.spbproductmanagementjwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CheckWithdrawBalance extends RuntimeException {

    public CheckWithdrawBalance(String message) {
        super(message);
    }
}
