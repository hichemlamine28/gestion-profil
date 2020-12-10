package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.profil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserAuth;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ProfilRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ldap.UserAuthLdapRepository;

/**
 *
 * @author bona
 */
@Service
public class ProfilRSMImpl implements ProfilRSM {

	@Value("${dataflow.search.admin.role}")
	private String adminRole;

	@Value("${dataflow.search.etl.role}")
	private String etlRole;

	@Autowired
	private ProfilRepository profilRepository;

	@Autowired
	private UserAuthLdapRepository userAuthLdapRepository;

	@Override
	public Profil getInformation(String username) {
		return profilRepository.getInformation(username);
	}

	@Override
	public List<Profil> getBykeyValidateProfil(String keyValidateProfil) {
		return profilRepository.getBykeyValidateProfil(keyValidateProfil);
	}

	@Override
	public Page<Profil> getContactInformationsByIds(List<String> ids, String type, String filter, Pageable pageable) {
		return profilRepository.getContactInformationsByIds(ids, type, filter, pageable);
	}

	@Override
	public List<Profil> getProfilsInformationsByIds(List<String> userIds) {
		return profilRepository.getProfilsInformationsByIds(userIds);
	}

	@Override
	public Page<Profil> getPaginatedProfilsInformationsByIds(List<String> userIds, Pageable pageable) {
		return profilRepository.getProfilsInformationsByIds(userIds, pageable);
	}

	@Override
	public Page<Profil> getNewSubscribedUsers(Pageable pageable) {
		Optional<List<UserAuth>> optionalAdmins = userAuthLdapRepository.findByRolesLikeIgnoreCase(adminRole);
		Optional<List<UserAuth>> optionalDataflowUsers = userAuthLdapRepository.findByRolesLikeIgnoreCase(etlRole);
		List<String> adminIds = new ArrayList<>(0);
		if (optionalAdmins.isPresent()) {
			List<UserAuth> admins = optionalAdmins.get();
			adminIds = admins.stream().map(UserAuth::getUsername).collect(Collectors.toList());
		}
		if (optionalDataflowUsers.isPresent()) {
			List<UserAuth> dataflowUsers = optionalDataflowUsers.get();
			adminIds.addAll(dataflowUsers.stream().map(UserAuth::getUsername).collect(Collectors.toList()));
		}
		return profilRepository.getNewSubscribedUsers(adminIds, pageable);
	}

	@Override
	public Profil getProfilById(String id) {
		return profilRepository.findById(id).orElse(null);
	}

	@Override
	public Page<Profil> getPaginatedProfilsInformations(List<String> userIds, String filter, String categorie,
			Pageable pageable) {
		return profilRepository.getProfilsInformationsByIds(userIds, filter, categorie, pageable);
	}

	@Override
	public Boolean isExistMail(String mail) {
		return profilRepository.isExistMail(mail);
	}

	@Override
	public List<Profil> findAll() {
		return profilRepository.findAll();
	}

	@Override
	public long count() {
		return profilRepository.count();
	}

	@Override
	public List<Profil> getProfilsInformationsFirstName(String firstName) {
		return profilRepository.findByFirstName(firstName);
	}

	@Override
	public Collection<? extends Profil> getListProfilByFirstNameConcatLastName(String concat) {
		return profilRepository.findByFirstNameConcatLastName(concat);
	}

	@Override
	public Collection<? extends Profil> findLastCreatedProfil() {
		return profilRepository.findLastCreatedProfil();

	}
}
