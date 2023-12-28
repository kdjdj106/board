package com.example.board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FormRegisterDto {
    private String uniqueId;
    private String password;

}
