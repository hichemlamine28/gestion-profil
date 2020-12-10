package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.profil_views;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjectSaveException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjetNotFoundException;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.ProfileView;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.ProfileViews;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.NumberOfViewsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilViewsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil.ProfilRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil_views.ProfilViewsRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.profil_views.ProfilViewsCUDSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.profil_views.ProfilViewsRSM;

/**
 *
 * @author njaka
 */
@Service
public class ProfilViewsCUDSAImpl implements ProfilViewsCUDSA {

	@Autowired
	private ProfilViewsRSM profilViewsRSM;

	@Autowired
	private ProfilViewsRSA profilViewsRSA;

	@Autowired
	private ProfilViewsCUDSM profilViewsCUDSM;

	@Autowired
	private ProfilRSA profilRSA;

	@Override
	public NumberOfViewsDTO save(String connectedUserId, String userId) {

		if (!StringUtils.equals(connectedUserId, userId)) {
			Date now = new Date();
			Boolean userAlreadySeen = false;

			ProfileView profileView = new ProfileView();
			profileView.setSeenDate(now);
			profileView.setUserId(connectedUserId);

			ProfilDTO profilDTO = profilRSA.getProfil(userId);

			if (profilDTO == null || profilDTO.isError()) {
				throw new ObjetNotFoundException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0007);
			}

			ProfileViews profileViews = profilViewsRSM.getByUserId(userId);

			if (profileViews != null) {
				if (!profileViews.getViewList().contains(profileView)) {
					profileViews.getViewList().add(profileView);
					profileViews.setModificationDate(now);
				} else {
					userAlreadySeen = true;
				}
			} else {
				profileViews = new ProfileViews();
				profileViews.setId(UUID.randomUUID().toString());
				profileViews.setUserId(userId);
				profileViews.getViewList().add(profileView);
				profileViews.setCreationDate(now);
			}

			if (!userAlreadySeen) {
				profileViews = profilViewsCUDSM.save(profileViews);
				if (profileViews == null) {
					throw new ObjectSaveException(new ProfilViewsDTO(), ErrorsEnum.ERR_MCS_PROFIL_0078);
				}
			}
		}

		return profilViewsRSA.getNbProfilViews(userId);
	}

}
