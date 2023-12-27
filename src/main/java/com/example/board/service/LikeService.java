package com.example.board.service;

import com.example.board.entity.Board;
import com.example.board.entity.Like;
import com.example.board.entity.User;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.LikeRepository;
import com.example.board.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    @Transactional
    public void increaseLike(String uniqueId, Long boardId) {
        // todo: 보드에 자신의 유니크아이디가 없는지 확인후 있으면 감소 있으면 그냥 진행
//        Board board = boardRepository.findById(boardId).get();
        User user = userRepository.findByUniqueId(uniqueId).get();
//        User boardUser = board.getUser();
        if (likeRepository.findByUserAndBoardId(user, boardId).isEmpty()){
            Optional<Board> optionalBoard = boardRepository.findById(boardId);
            Board board;
            if (optionalBoard.isPresent()) {
                board = optionalBoard.get();
            } else throw new NullPointerException();

            board.changeLikeCnt(board.getLikeCnt() + 1);

            likeRepository.save(Like.builder()
                    .user(user)
                    .board(board)
                    .build());
        }else {
            System.out.println("cant likeBoard : cant find in likeRepository");
        }




    }

    @Transactional
    public void decreaseLike(String uniqueId, Long boardId) {
        // todo: 보드에 자신의 유니크아이디가 있는지 확인후 있으면 감소 없으면 그냥 진행



        User user = userRepository.findByUniqueId(uniqueId).get();
//        User boardUser = board.getUser();

        if (likeRepository.findByUserAndBoardId(user, boardId).isPresent()){
            Optional<Board> optionalBoard = boardRepository.findById(boardId);
            Board board;
            if (optionalBoard.isPresent()) {
                board = optionalBoard.get();
            } else throw new NullPointerException();

            board.changeLikeCnt(board.getLikeCnt() - 1);

            likeRepository.deleteByUserUniqueIdAndBoardId(uniqueId, boardId);
        }else {
            System.out.println("cant deleteLike : cant find in likeRepository");
        }
    }

    public List<Like> getLikeList(Long boardId){
        List<Like> likeList =  new ArrayList<>();
        return likeRepository.findAllByBoardId(boardId);
    }


}

