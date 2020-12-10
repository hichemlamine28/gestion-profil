package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserAuth;

public interface UserCUDSA {


    UserAuth create(String firstName, String lastName, String username, String pseudoName, String password);

    UserAuth modify(String u, String p);

    void deleteAll();
}
