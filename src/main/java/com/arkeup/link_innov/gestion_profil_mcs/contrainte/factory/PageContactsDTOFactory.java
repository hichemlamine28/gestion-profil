package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory;

import org.springframework.data.domain.Page;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.PageContactsDTO;

/**
 * 
 * @author Jéremy
 *
 */
public interface PageContactsDTOFactory {
	PageContactsDTO getInstance();
	
	PageContactsDTO getInstance(Page<Profil> pageProfils);
}
