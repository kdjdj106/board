package com.example.board.converters;


import com.example.board.model.ProviderUser;
import com.example.board.model.social.GoogleUser;
import com.example.board.type.OAuth2Config;
import com.example.board.util.OAuth2Utils;

public class OAuth2GoogleProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser>{
    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {

        if (!providerUserRequest.clientRegistration().getRegistrationId().equals(OAuth2Config.SocialType.GOOGLE.getSocialName())){
            return null;
        }

        return new GoogleUser(OAuth2Utils.getMainAttributes(providerUserRequest.oAuth2User())
        , providerUserRequest.oAuth2User()
        , providerUserRequest.clientRegistration());
    }
}
