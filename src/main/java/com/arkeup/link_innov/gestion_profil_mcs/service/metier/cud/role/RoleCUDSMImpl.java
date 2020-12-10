package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.role;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Role;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ldap.RoleRepository;

/**
 * Created by bgh on 18/10/18.
 */
@Service
public class RoleCUDSMImpl implements RoleCUDSM {
	
	@Value("${ldap.spring.peopleBase}")
	private String peopleDN;
	
	@Value("${ldap.spring.groupsBase}")
	private String groupsDN;
	
	@Value("${ldap.base}")
	private String baseDN;

    @Autowired
	private RoleRepository roleRepository;
    

    @Override
	public Role addUserToGroup(String roleName, String userName) {
		
		Role role = findRoleByName(roleName);
		if (role != null) {
			List<String> uniqueMembers = role.getMember();
			uniqueMembers.add("uid="+userName+","+peopleDN+","+baseDN);
			role.setMember(uniqueMembers);
			return roleRepository.save(role);
		}
		return null;
		
	}
	
	public Role findRoleByName(String roleName) {
		Role role = null;
		Optional<Role> optionalRoleLdap = roleRepository.findById(LdapUtils.newLdapName("cn="+roleName+","+groupsDN));
		if (optionalRoleLdap.isPresent()) {
			role = optionalRoleLdap.get();
		}
		return role;
	}
	
	
}
