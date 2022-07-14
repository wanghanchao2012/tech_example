package com.example.sign.domain;

import lombok.*;

@Builder
@AllArgsConstructor
@ToString
@Data
public class ResponseModel<T> {

    private String code;
    private String message;
    private T data;

    public ResponseModel() {
        this.code = "200";
        this.message = "ok";
    }

    public ResponseModel(T data) {
        this.data = data;
        this.code = "200";
        this.message = "ok";
    }
}
