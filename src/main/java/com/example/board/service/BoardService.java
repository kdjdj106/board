package com.example.board.service;

import com.example.board.dto.BoardsDto;
import com.example.board.dto.BoardDto;
import com.example.board.entity.Board;
import com.example.board.entity.User;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.LikeRepository;
import com.example.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;

    public void saveBoard(BoardDto boardDto, String uniqueId) {
        User user = userRepository.findByUniqueId(uniqueId).orElseThrow(NullPointerException::new);
        Board board = Board.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .hashTag(boardDto.getHashTag())
                .weatherUrl(boardDto.getWeatherUrl())
                .user(user)
                .build();

        boardRepository.save(board);
    }

//    public void updateBoard(BoardsDto boardsDto, Long boardId){
//        Optional<Board> boardOptional = boardRepository.findById(boardId);
//        Board board;
//        if (boardOptional.isPresent()){
//            board = boardOptional.get();
//        }else throw new NullPointerException("updateBoard : 게시글을 찾을수 없습니다.");
//        board.updateBoard(boardsDto.getTitle(), boardsDto.);
//    }


    public List<BoardsDto> getAllBoards() {
        List<Board> boards = boardRepository.findAllBoard();
        List<BoardsDto> boardsDtoList = new ArrayList<>();
        for (Board board : boards) {
            boardsDtoList.add(new BoardsDto(board));
        }
        return boardsDtoList;
    }

    public List<BoardsDto> getMyBoards(String uniqueId) {
        List<BoardsDto> myBoards = new ArrayList<>();
        List<Board> myBoard = boardRepository.findByUserUniqueId(uniqueId);
        for (Board board : myBoard) {
            myBoards.add(BoardsDto.builder()
                    .id(board.getId())
                    .title(board.getTitle())
                    .commentCnt(board.getCommentCnt())
                    .lickCnt(board.getLikeCnt())
                    .weatherUrl(board.getWeatherUrl())
                    .hashTag(board.getHashTag())
                    .build());
        }
        return myBoards;
    }





}
