package com.sysman.prueba.models.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse<T> {

    private int status;
    private String message;
    private T data;
}
