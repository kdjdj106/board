package com.example.board.converters;


import com.example.board.entity.User;
import com.example.board.model.ProviderUser;

public final class UserDetailsProviderUserConverter implements ProviderUserConverter<ProviderUserRequest,ProviderUser> {


    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {
            if(providerUserRequest.user() == null){
                return null;
            }

            User user = providerUserRequest.user();
            return User.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .authorities(user.getAuthorities())
                    .email(user.getEmail())
                    .provider("none")
                    .build();
        }
    }
