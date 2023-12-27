package com.example.board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardDto {

    private String title;
    private String content;
    private String hashTag;
    private String weatherUrl;


}
