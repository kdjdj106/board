package com.example.board.controller;

import com.example.board.entity.Like;
import com.example.board.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    @GetMapping("/getLikeList")
    public ResponseEntity<List<Like>> getLikeList(@RequestHeader("HEADER")  String uniqueId, @RequestParam Long boardId){
        List<Like> likelist = likeService.getLikeList(boardId);
        return ResponseEntity.ok(likelist);
    }

    @PostMapping("/increaseLike")
    public ResponseEntity<?> increaseLike(@RequestHeader("HEADER")  String uniqueId, @RequestParam Long boardId){
        likeService.increaseLike(uniqueId, boardId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/decreaseLike")
    public ResponseEntity<?> decreaseLike(@RequestHeader("HEADER")  String uniqueId, @RequestParam Long boardId){
        likeService.decreaseLike(uniqueId, boardId);
        return ResponseEntity.ok().build();
    }
}
