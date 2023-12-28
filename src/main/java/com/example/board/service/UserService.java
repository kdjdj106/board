package com.example.board.service;

import com.example.board.dto.FormRegisterDto;
import com.example.board.model.ProviderUser;
import com.example.board.repository.CommentRepository;
import com.example.board.repository.LikeRepository;
import com.example.board.repository.UserRepository;
import com.example.board.dto.MyInfoDto;
import com.example.board.entity.User;
import com.example.board.type.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    private final BCryptPasswordEncoder encoder;

    public MyInfoDto myInfo(String nickname) {
        User user = userRepository.findByUniqueId(nickname).orElseThrow(NullPointerException::new);

        return MyInfoDto.builder()
                .nickName(user.getNickName())
                .userRole(user.getUserRole())
                .boards(user.getBoards())
                .build();


    }

    //todo: 유저정보저장 수정필요
    public void register(String registrationId, ProviderUser providerUser) {
        String uniqueId = providerUser.getEmail() + providerUser.getProvider();
        User user = User.builder().uniqueId(uniqueId)
//                .id(providerUser.getId())
//                .username(providerUser.getUsername())
                .password(providerUser.getPassword())
                .userRole(UserRole.USER)
//                .authorities(providerUser.getAuthorities())
                .provider(registrationId)
                .email(providerUser.getEmail())
                .build();

        userRepository.save(user);
    }

    public void register(FormRegisterDto registerDto) {
        String encryptPW = encoder.encode(registerDto.getPassword());
        User user = User.builder()
                .uniqueId(registerDto.getUniqueId())
                .password(encryptPW)
                .userRole(UserRole.USER)
                .provider("FORM")
                .email("FORM")
                .build();

        userRepository.save(user);
    }


}
