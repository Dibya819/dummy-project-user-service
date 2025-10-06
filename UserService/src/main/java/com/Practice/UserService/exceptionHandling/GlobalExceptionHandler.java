package com.Practice.UserService.exceptionHandling;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.Practice.UserService.dtos.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handelUserNotFound (UserNotFoundException ex, HttpServletRequest request) {
        String uri=request.getRequestURI();
        ErrorResponse errorResponse=new ErrorResponse(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "User Not found!!!",ex.getMessage(),uri);
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse>handleValidationErrors(MethodArgumentNotValidException ex,HttpServletRequest request){
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .toList();
        ErrorResponse errorResponse=new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Invalid Input!!",
                errors,
                request.getRequestURI());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalExceptions(Exception ex,HttpServletRequest request){
        ErrorResponse errorResponse=new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage(),
                request.getRequestURI());
        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
