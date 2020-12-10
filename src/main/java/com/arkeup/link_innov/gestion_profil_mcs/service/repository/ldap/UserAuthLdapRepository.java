package com.arkeup.link_innov.gestion_profil_mcs.service.repository.ldap;

import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.stereotype.Repository;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserAuth;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAuthLdapRepository extends LdapRepository<UserAuth> {
	
    UserAuth findByUsername(String username);
    UserAuth findByUsernameAndPassword(String username, String password);
    List<UserAuth> findByUsernameLikeIgnoreCase(String username);
    UserAuth findByMail(String mail);
    UserAuth findByPseudoName(String pseudoName);
	Optional<List<UserAuth>> findByRolesLikeIgnoreCase(String role); // Benj 20/12/2018
}