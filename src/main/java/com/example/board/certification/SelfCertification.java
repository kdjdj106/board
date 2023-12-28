package com.example.board.certification;

import com.example.board.entity.User;
import com.example.board.model.ProviderUser;
import com.example.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SelfCertification {

    private final UserRepository userRepository;
    public void checkCertification(ProviderUser providerUser) {
        Optional<User> user = userRepository.findByUniqueId(providerUser.getUniqueId());
//        if(user != null) {
        boolean bool = providerUser.getProvider().equals("FORM") || providerUser.getProvider().equals("naver");
        providerUser.isCertificated(bool);
//        }
    }

    public void certificate(ProviderUser providerUser) {

    }
}
