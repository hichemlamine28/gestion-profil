package com.arkeup.link_innov.gestion_profil_mcs.service.repository.ldap;

import java.util.List;

import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Role;

/**
 * Created by bgh on 18/10/18.
 */
@Repository
public interface RoleRepository extends LdapRepository<Role> {
	
	List<Role> findByMemberLikeIgnoreCase(String uniqueMember);

}