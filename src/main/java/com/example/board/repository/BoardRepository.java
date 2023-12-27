package com.example.board.repository;

import com.example.board.entity.Board;
import com.example.board.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    @Query(value = "select b from Board  b")
    List<Board> findAllBoard();

    @Query(value = "select b from  Board b where b.user.uniqueId =:uniqueId")
    List<Board> findByUserUniqueId(@Param("uniqueId") String uniqueId);

    @Query(value = "select b from  Board b where b.id =:boardId")
    Optional<Board> findById(@Param("boardId") Long boardId);

}
