package com.example.board.service.login;

import com.example.board.certification.SelfCertification;
import com.example.board.converters.ProviderUserConverter;
import com.example.board.converters.ProviderUserRequest;
import com.example.board.entity.User;
import com.example.board.model.ProviderUser;
import com.example.board.repository.UserRepository;
import com.example.board.service.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Getter
public abstract class AbstractOAuth2UserService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SelfCertification certification;
    @Autowired
    private ProviderUserConverter<ProviderUserRequest, ProviderUser> providerUserConverter;

    public void selfCertificate(ProviderUser providerUser){
        certification.checkCertification(providerUser);
    }
    public void register(ProviderUser providerUser, OAuth2UserRequest userRequest){

        String uniqueId = providerUser.getEmail()+providerUser.getProvider();
        //todo : 유저 찾는 방식 수정필요
        Optional<User> user = userRepository.findByUniqueId(uniqueId);

        if(user.isEmpty()){
            ClientRegistration clientRegistration = userRequest.getClientRegistration();
            userService.register(clientRegistration.getRegistrationId(),providerUser);
        }else{
            System.out.println("이미 회원가입 되어있습니다.");
            System.out.println("userRequest = " + userRequest);
        }
    }

    public ProviderUser providerUser(ProviderUserRequest providerUserRequest){

        return providerUserConverter.converter(providerUserRequest);
    }
}
