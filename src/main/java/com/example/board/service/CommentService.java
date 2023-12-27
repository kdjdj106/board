package com.example.board.service;

import com.example.board.dto.CommentDto;
import com.example.board.entity.Board;
import com.example.board.entity.Comment;
import com.example.board.entity.Like;
import com.example.board.entity.User;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public void addComment(String uniqueId, Long boardId, CommentDto commentDto) {
        // todo: 코멘트 추가
//        Board board = boardRepository.findById(boardId).get();
        User user = userRepository.findByUniqueId(uniqueId).get();
//        User boardUser = board.getUser();


        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        Board board;
        if (optionalBoard.isPresent()) {
            board = optionalBoard.get();

            commentRepository.save(Comment.builder()
                    .content(commentDto.getContent())
                    .user(user)
                    .board(board)
                    .build());

        } else throw new NullPointerException("게시글이 없습니다.");


    }


    @Transactional
    public void deleteComment(String uniqueId, Long commentId) {
        // todo: 보드에 자신의 유니크아이디가 있는지 확인후 있으면 감소 없으면 그냥 진행


        User user = userRepository.findByUniqueId(uniqueId).get();
//        User boardUser = board.getUser();


        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if (optionalComment.isPresent()) {
            commentRepository.deleteById(commentId);
        } else throw new NullPointerException("댓글이 없습니다.");


    }

    public List<Comment> getCommentList(Long boardId){
        Optional<Board>optionalBoard = boardRepository.findById(boardId);
        Board board;
        if (optionalBoard.isPresent()) board = optionalBoard.get();
        else throw new NullPointerException();
        List<Comment> commentList = commentRepository.findAllByBoardId(boardId);
        if (!commentList.isEmpty()) return commentList;
        else throw new NullPointerException("댓글리스트가 비었습니다.");
    }

    public List<Comment> getMyCommentList(String uniqueId){
        Optional<User> optionalUser = userRepository.findByUniqueId(uniqueId);
        User user;
        if (optionalUser.isPresent()) user = optionalUser.get();
        else throw new NullPointerException("유저를 찾을수 없습니다.");
        List<Comment> commentList = commentRepository.findAllByUserUniqueId(uniqueId);
        if (!commentList.isEmpty()) return commentList;
        else throw new NullPointerException("댓글리스트가 비었습니다.");
    }




}
