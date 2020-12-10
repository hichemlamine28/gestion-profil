/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.reseausocial;

import org.springframework.stereotype.Component;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.ReseauSocialUserDTO;

/**
 * @author mikajy
 *
 */
@Component
public class ReseauSocialUserDTOFactoryImpl implements ReseauSocialUserDTOFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.reseausocial.
	 * ReseauSocialUserDTOFactory#getInstance()
	 */
	@Override
	public ReseauSocialUserDTO getInstance() {
		return new ReseauSocialUserDTO();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.reseausocial.
	 * ReseauSocialUserDTOFactory#getInstance(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public ReseauSocialUserDTO getInstance(String id, String lastname, String firstname) {
		ReseauSocialUserDTO reseauSocialUserDTO = new ReseauSocialUserDTO();
		reseauSocialUserDTO.setId(id);
		reseauSocialUserDTO.setFirstName(firstname);
		reseauSocialUserDTO.setLastName(lastname);
		return reseauSocialUserDTO;
	}

	@Override
	public ReseauSocialUserDTO getInstance(Profil profil) {
		ReseauSocialUserDTO reseauSocialUserDTO = new ReseauSocialUserDTO();
		reseauSocialUserDTO.setId(profil.getUsername());
		reseauSocialUserDTO.setFirstName(profil.getFirstname());
		reseauSocialUserDTO.setLastName(profil.getLastname());
		reseauSocialUserDTO.setEmployerName(profil.getCompany() != null ? profil.getCompany().getName() : null);
		reseauSocialUserDTO.setProfession(profil.getOccupation());
		reseauSocialUserDTO.setType(profil.getCategory() != null ? profil.getCategory().getId() : null);
		return reseauSocialUserDTO;
	}

}
