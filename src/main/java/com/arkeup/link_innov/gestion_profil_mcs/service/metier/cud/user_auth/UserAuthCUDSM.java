package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.user_auth;

import java.util.Date;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserAuth;

/**
 * Created by bgh on 18/10/18.
 */
public interface UserAuthCUDSM {

    public UserAuth create(UserAuth newUserAuth);
    public void deleteByUsername(String username);
    public UserAuth findByUsername(String username);
    public UserAuth findByPseudonName(String pseudoName);
    public Boolean isPseudoExist(String pseudoName);
    public Boolean isMailExist(String mail);
    public UserAuth findByMail(String mail);
    
    void userAuthLastConnexion(String username);
    Date getDateOflastConnexion(String username);

}
