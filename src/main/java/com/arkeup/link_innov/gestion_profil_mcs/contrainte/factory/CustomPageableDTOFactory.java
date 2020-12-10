package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory;

import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CustomPageableDTO;

/**
 * 
 * @author Jéremy
 *
 */
public interface CustomPageableDTOFactory {
	
	public CustomPageableDTO getInstance();
	
	public CustomPageableDTO getInstance(Pageable pageable);
}
