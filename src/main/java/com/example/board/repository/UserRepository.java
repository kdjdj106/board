package com.example.board.repository;

import com.example.board.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {


//    Optional<User> findById(Long id);
    @Query(value = "select user " + "from  User user " + "where user.id = :id")
    Optional<User> findById(@Param("id") Long id);

//    @Query(value = "select user from User user where user.uniqueId = :uniqueId")
//    Optional<User> findByUniqueId(@Param("uniqueId") String uniqueId);

    Optional<User> findByUniqueId(String uniqueId);
    Page<User> findAllByNickNameContains(String nickname, PageRequest pageRequest);
//    Boolean existsByNickname(String nickname);
}
