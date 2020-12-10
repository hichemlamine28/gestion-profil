package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory;

import org.springframework.data.domain.Sort;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CustomSortDTO;

/**
 * 
 * @author Jéremy
 *
 */
public interface CustomSortDTOFactory {
	
	public CustomSortDTO getInstance();
	
	public CustomSortDTO getInstance(Sort sort);
}
