package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.UserFactory;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserAuth;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.UserCUDSM;

@Service
public class UserCUDSAImpl implements UserCUDSA {

    @Autowired
    private UserCUDSM userCUDSM;

    @Autowired
    private UserFactory userFactory;

    @Override
    public UserAuth create(String firstName, String lastName, String username, String pseudoName, String password){

        UserAuth userAuth = userFactory.getInstance(firstName,lastName,username, pseudoName, password);

        return userCUDSM.create(userAuth);
    }


    public UserAuth modify(String u, String p) {
        return userCUDSM.modify(u,p);
    }


    @Override
    public void deleteAll() {
        userCUDSM.deleteAll();
    }
}
