package com.example.board.converters;


import com.example.board.entity.User;
import com.example.board.model.ProviderUser;
import com.example.board.type.UserRole;
import com.nimbusds.oauth2.sdk.Role;

public final class UserDetailsProviderUserConverter implements ProviderUserConverter<ProviderUserRequest,ProviderUser> {


    @Override
    public ProviderUser converter(ProviderUserRequest providerUserRequest) {
            if(providerUserRequest.user() == null){
                return null;
            }

            User user = providerUserRequest.user();
             User.builder()
                    .uniqueId(user.getUniqueId())
                    .password(user.getPassword())
                    .userRole(user.getUserRole())
//                    .authorities(user.getAuthorities())
                    .email(user.getEmail())
                    .provider("FORM")
                    .build();

        return user;
        }
    }
