package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.profil_views;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.ProfileViews;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.ProfilViewsMongoRepository;

/**
 *
 * @author njaka
 */

@Service
public class ProfilViewsCUDSMImpl implements ProfilViewsCUDSM {

	@Autowired
	private ProfilViewsMongoRepository profilViewsMongoRepository;

	@Override
	public ProfileViews save(ProfileViews profileViews) {
		return profilViewsMongoRepository.save(profileViews);
	}
}
