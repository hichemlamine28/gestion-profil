package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserAuth;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ldap.UserAuthLdapRepository;

@Service
public class UserCUDSMImpl implements UserCUDSM {

    @Autowired
    private UserAuthLdapRepository userAuthLdapRepository;

    @Override
    public UserAuth create(UserAuth newUserAuth){

        return userAuthLdapRepository.save(newUserAuth);
    }

    @Override
    public UserAuth modify(String u, String p) {
        UserAuth userAuth = userAuthLdapRepository.findByUsername(u);
        userAuth.setPassword(p);
        return userAuthLdapRepository.save(userAuth);
    }

    @Override
    public void deleteAll(){
        userAuthLdapRepository.deleteAll();
    }
}
