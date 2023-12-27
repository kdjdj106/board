package com.example.board.converters;


import com.example.board.model.ProviderUser;
import com.example.board.model.social.NaverUser;
import com.example.board.type.OAuth2Config;
import com.example.board.util.OAuth2Utils;

public class OAuth2NaverProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser>{
    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {
        if (!providerUserRequest.clientRegistration().getRegistrationId().equals(OAuth2Config.SocialType.NAVER.getSocialName())){
            return null;
        }

        return new NaverUser(OAuth2Utils.getSubAttributes(providerUserRequest.oAuth2User(), "response")
                , providerUserRequest.oAuth2User()
                , providerUserRequest.clientRegistration());
    }

    }

