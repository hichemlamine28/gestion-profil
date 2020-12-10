package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CustomSortDTO;

/**
 * 
 * @author Jéremy
 *
 */
@Component
public class CustomSortDTOFactoryImpl implements CustomSortDTOFactory {

	@Override
	public CustomSortDTO getInstance() {
		return new CustomSortDTO();
	}

	@Override
	public CustomSortDTO getInstance(Sort sort) {
		CustomSortDTO instance = getInstance();
		
		instance.setSorted(sort.isSorted());
		instance.setUnsorted(sort.isUnsorted());
		
		return instance;
	}

}
