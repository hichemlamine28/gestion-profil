package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.admin;

import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.admin.AdminSM;
import org.apache.lucene.util.automaton.Operations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Arrays;

@Service
public class AdminSAImpl implements AdminSA {

    @Autowired
    private AdminSM adminSM;

    @Async
    @Override
    public void reindexAllES(){
        adminSM.reindexAllES();

    }
}
