package com.example.board.dto;

import com.example.board.entity.Board;
import com.example.board.entity.Comment;
import com.example.board.entity.Like;
import com.example.board.type.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class MyInfoDto {
    //private String uniqueId;
    private String nickName;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private List<Board> boards;
//    private List<Like> likes;
//    private List<Comment> comments;
}
