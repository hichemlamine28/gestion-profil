package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.information;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserAuth;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserInformation;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.UserInformationRepository;

@Service
public class UserInformationCUDSMImpl implements UserInformationCUDSM {

	@Autowired
	private UserInformationRepository userInformationRepository;

	//@Autowired
	//private UserAuthLdapRepository ldapRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserInformation create(UserInformation userInformation) {
		UserAuth ldapUser = new UserAuth();
		ldapUser.setUsername(userInformation.getEmail());
		ldapUser.setPassword(userInformation.getPassword());
		ldapUser.setFullName(userInformation.getFirstname()+" "+userInformation.getLastname());
		ldapUser.setFullName(userInformation.getUsername());
		//ldapRepository.save(ldapUser);
		userInformation.setPassword(passwordEncoder.encode(userInformation.getPassword()));
		userInformationRepository.save(userInformation);

		return userInformation;
	}

	@Override
	public void update(UserInformation userInformation) {
		userInformationRepository.save(userInformation);
	}
}
