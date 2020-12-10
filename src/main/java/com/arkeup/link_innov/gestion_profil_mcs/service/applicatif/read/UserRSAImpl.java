package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.UserRSM;

import java.util.List;

@Service
public class UserRSAImpl implements UserRSA {

    @Autowired
    private UserRSM userRSM;

    public List<String> search(String u){
        return userRSM.search(u);
    }
}
