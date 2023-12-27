package com.example.board.converters;

import com.example.board.model.ProviderUser;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Component
public class DelegatingProviderUserConverter implements ProviderUserConverter<ProviderUserRequest, ProviderUser> {
    private List<ProviderUserConverter<ProviderUserRequest, ProviderUser>> converters;

    public DelegatingProviderUserConverter(){
        List<ProviderUserConverter<ProviderUserRequest, ProviderUser>> providerUserConverters =
                Arrays.asList(
                        // 폼인증이 가장먼저 실행되게 한다.
                        new UserDetailsProviderUserConverter(),
                        new OAuth2GoogleProviderUserConverter(),
                        new OAuth2NaverProviderUserConverter(),
                        new OAuth2KakaoProviderUserConverter(),
                        new OAuth2KakaoOidcProviderUserConverter()
                );

        this.converters = Collections.unmodifiableList(new LinkedList<>(providerUserConverters));
    }
    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {
        Assert.notNull(providerUserRequest, "providerUserRequest cannot be null");

        for (ProviderUserConverter<ProviderUserRequest, ProviderUser> converter : converters){
            ProviderUser provideruser = converter.converter(providerUserRequest);
            if (provideruser != null) return provideruser;
        }
        return null;
    }
}
