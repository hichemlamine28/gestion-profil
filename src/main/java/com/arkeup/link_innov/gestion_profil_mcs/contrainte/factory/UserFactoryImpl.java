package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory;

import org.springframework.stereotype.Component;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserAuth;

@Component
public class UserFactoryImpl implements UserFactory {

    @Override
    public UserAuth getInstance(){
        return new UserAuth();
    }

    @Override
    public UserAuth getInstance(String firstName, String lastName, String username, String pseudoName, String password){
        UserAuth userAuth = getInstance();

        userAuth.setPseudoName(pseudoName);
        userAuth.setFullName(firstName + " " + lastName);
        userAuth.setUsername(username);
        userAuth.setPassword(password);

        return userAuth;
    }
}
