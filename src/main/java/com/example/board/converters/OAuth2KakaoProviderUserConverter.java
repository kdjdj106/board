package com.example.board.converters;

import com.example.board.model.ProviderUser;
import com.example.board.model.social.NaverUser;
import com.example.board.type.OAuth2Config;
import com.example.board.util.OAuth2Utils;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class OAuth2KakaoProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser>{
    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {
        if (!providerUserRequest.clientRegistration().getRegistrationId().equals(OAuth2Config.SocialType.KAKAO.getSocialName())) {
            return null;
        }

        if (providerUserRequest.oAuth2User() instanceof OidcUser) {
            return null;
        }

        return new NaverUser(OAuth2Utils.getOtherAttributes(providerUserRequest.oAuth2User(), "kakao_account", "profile")
                , providerUserRequest.oAuth2User()
                , providerUserRequest.clientRegistration());
    }

    }

