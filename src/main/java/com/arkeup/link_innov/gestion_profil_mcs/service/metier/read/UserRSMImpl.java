package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserAuth;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ldap.UserAuthLdapRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRSMImpl implements UserRSM {

    @Autowired
    private UserAuthLdapRepository userAuthLdapRepository;

    public List<String> search(String u){
        List<UserAuth> userAuthList = userAuthLdapRepository
                .findByUsernameLikeIgnoreCase(u);

        if (userAuthList == null) {
            return Collections.emptyList();
        }

        return userAuthList.stream()
                .map(UserAuth::getUsername)
                .collect(Collectors.toList());
    }
}
