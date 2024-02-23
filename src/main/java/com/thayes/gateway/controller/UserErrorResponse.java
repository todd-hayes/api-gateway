package com.thayes.gateway.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @NoArgsConstructor
public class UserErrorResponse {
    private int status;
    private String message;
    private long timestamp;
}
