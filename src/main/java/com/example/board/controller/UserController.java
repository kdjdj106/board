package com.example.board.controller;

import com.example.board.dto.MyInfoDto;
import com.example.board.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/myInfo")
    public ResponseEntity<MyInfoDto> getMyInfo(@RequestHeader("HEADER") String userNickname){
        MyInfoDto myInfoDto = userService.myInfo(userNickname);
        return ResponseEntity.ok()
                .body(myInfoDto);
    }

}
