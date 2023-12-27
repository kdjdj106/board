package com.example.board.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LikeBoardDto {

    private String uniqueId;
    private Long boardId;

}
