package com.Practice.UserService.dtos;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private List<String> messages;
    private String path;

    public ErrorResponse(LocalDateTime timestamp,int status,String error,String message,String path){
        this.timestamp=timestamp;
        this.status=status;
        this.error=error;
        this.message=message;
        this.path=path;
    }
    public ErrorResponse(LocalDateTime timestamp, int status, String error, List<String> messages, String path){
        this.timestamp=timestamp;
        this.status=status;
        this.error=error;
        this.messages=messages;
        this.path=path;
    }
}
