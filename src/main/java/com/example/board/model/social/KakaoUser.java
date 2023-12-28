package com.example.board.model.social;

import com.example.board.model.Attributes;
import com.example.board.model.OAuth2ProviderUser;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class KakaoUser extends OAuth2ProviderUser {

    private final Map<String,Object> otherAttributes;

    public KakaoUser(Attributes attributes, OAuth2User oAuth2User, ClientRegistration clientRegistration) {
        super(attributes.getSubAttributes(), oAuth2User, clientRegistration);
        this.otherAttributes = attributes.getOtherAttributes();

    }

    @Override
    public String getId() {
        return (String)getAttributes().get("id");
    }

    @Override
    public String getUsername() {
        return (String) otherAttributes.get("nickname");
    }

    @Override
    public String getPicture() {
        return (String) otherAttributes.get("profile_image_url");
    }

    @Override
    public String getUniqueId() {
        return null;
    }

}
