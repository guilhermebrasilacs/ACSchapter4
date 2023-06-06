package com.project2.ACSchapter4.advice;

import	org.springframework.http.HttpStatus;
import	org.springframework.web.bind.annotation.ControllerAdvice;
import	org.springframework.web.bind.annotation.ExceptionHandler;
import	org.springframework.web.bind.annotation.ResponseBody;
import	org.springframework.web.bind.annotation.ResponseStatus;

import com.project5.ACSchapter8.shoppingclient.dto.ErrorDTO;
import com.project5.ACSchapter8.shoppingclient.exception.UserNotFoundException;

import java.time.LocalDateTime;

@ControllerAdvice(basePackages = "com.project2.ACSchapter4.controller")
public class UserControllerAdvice {
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ErrorDTO handlerUserNotFound(UserNotFoundException userNotFoundException){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setStatus(HttpStatus.NOT_FOUND.value());
        errorDTO.setMessage("Usuário não encontrado.");
        errorDTO.setTimestamp(LocalDateTime.now());
        return errorDTO;
    }
}
