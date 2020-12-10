package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserAuth;

public interface UserCUDSM {


    UserAuth create(UserAuth newUserAuth);

    public UserAuth modify(String u, String p);

    void deleteAll();
}
