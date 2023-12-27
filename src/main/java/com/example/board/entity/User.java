package com.example.board.entity;

import com.example.board.type.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uniqueId;
    private String nickName;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Board> boards = new ArrayList<>();     // 작성글

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();       // 유저가 누른 좋아요

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>(); // 댓글


}
