package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.role.RoleRSM;

/**
 * Created by bgh on 18/10/18.
 */
@Service
public class RoleRSAImpl implements RoleRSA {

    @Autowired
    private RoleRSM getRoleSM;


	@Override
	public List<String> getUserRoles(String userName) {
		return getRoleSM.getUserRoles(userName);
	}
}
