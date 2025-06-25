package com.LegendOfBrains.singo_server.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDTO {
    private Long id;
    private String message;
    private Object data;

    public ResponseDTO(String message, EnrollDTO enrollDTO) {
        this.message = message;
        this.data = null;
    }
}
