package com.example.demo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseResponse {
    private Integer status;
    private String message;

    @JsonProperty("status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
