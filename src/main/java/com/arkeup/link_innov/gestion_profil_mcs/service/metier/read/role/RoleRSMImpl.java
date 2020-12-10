package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.role;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Role;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ldap.RoleRepository;

/**
 * Created by bgh on 18/10/18.
 */
@Service
public class RoleRSMImpl implements RoleRSM {
	
	
	@Value("${ldap.spring.peopleBase}")
	private String peopleDN;
	
	@Value("${ldap.base}")
	private String baseDN;

    @Autowired
	private RoleRepository roleRepository;
    

	@Override
	public List<String> getUserRoles(String userName) {
		List<String> roles = new ArrayList<>();
		List<Role> listRolesLdap = roleRepository.findByMemberLikeIgnoreCase("uid="+userName+","+peopleDN+","+baseDN);
    	for (Role roleLdap : listRolesLdap) {
			roles.add(roleLdap.getRoleName().toUpperCase());
		}
    	return roles;
	}
	
	

}
