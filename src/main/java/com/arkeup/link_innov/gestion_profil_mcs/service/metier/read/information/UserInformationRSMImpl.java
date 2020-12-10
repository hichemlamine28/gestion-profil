package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.information;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserAuth;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserInformation;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.UserInformationRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ldap.UserAuthLdapRepository;

@Service
public class UserInformationRSMImpl implements UserInformationRSM {

	@Autowired
	UserAuthLdapRepository userLdapRepository;
	
	@Autowired
	private UserInformationRepository userInformationRepository;

	@Override
	public UserInformation getInformation(String username) {
		return userInformationRepository.getInformation(username);
	}

	@Override
	public UserAuth getUserByPseudo(String pseudoName) {
		return userLdapRepository.findByPseudoName(pseudoName);
	}


}
