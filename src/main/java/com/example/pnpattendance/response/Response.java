package com.example.pnpattendance.response;

import lombok.Data;

@Data
public class Response {

    private String message;
    private String status;
    private Object data;

    public Response(String message, String status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }
}
