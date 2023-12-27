package com.example.board.controller;

import com.example.board.dto.BoardDto;
import com.example.board.dto.BoardsDto;
import com.example.board.service.BoardService;
import com.example.board.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final LikeService likeService;

    @PostMapping("/saveBoard")
    public ResponseEntity<?> saveBoard(@RequestHeader("HEADER") String uniqueId, @RequestBody BoardDto boardDto) {

        boardService.saveBoard(boardDto, uniqueId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/getMyBoardList")
    public ResponseEntity<List<BoardsDto>> getBoardList(@RequestHeader("HEADER") String uniqueId) {
        List<BoardsDto> boardsDtoList = boardService.getMyBoards(uniqueId);
        return ResponseEntity.ok(boardsDtoList);
    }
    @GetMapping("/getAllBoards")
    public ResponseEntity<List<BoardsDto>> getAllBoards(){
        List<BoardsDto> boardsDtoList = boardService.getAllBoards();
        return ResponseEntity.ok(boardsDtoList);
    }






}