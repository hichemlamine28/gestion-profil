package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserAuth;

public interface UserFactory {
    UserAuth getInstance();

    UserAuth getInstance(String firstName, String lastName, String username, String pseudoName, String password);

}
