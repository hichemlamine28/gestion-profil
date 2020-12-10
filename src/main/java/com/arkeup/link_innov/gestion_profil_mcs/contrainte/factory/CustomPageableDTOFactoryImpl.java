package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CustomPageableDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CustomSortDTO;

/**
 * 
 * @author Jéremy
 *
 */
@Component
public class CustomPageableDTOFactoryImpl implements CustomPageableDTOFactory {
	
	@Autowired
	CustomSortDTOFactory customSortDTOFactory;
	
	@Override
	public CustomPageableDTO getInstance() {
		return new CustomPageableDTO();
	}

	@Override
	public CustomPageableDTO getInstance(Pageable pageable) {
		CustomPageableDTO instance = getInstance();
		CustomSortDTO customSortDTO = customSortDTOFactory.getInstance(pageable.getSort());
		
		instance.setSort(customSortDTO);
		instance.setPageSize(pageable.getPageSize());
		instance.setPageNumber(pageable.getPageNumber());
		instance.setOffset(pageable.getOffset());
		instance.setUnpaged(pageable.isUnpaged());
		instance.setPaged(pageable.isPaged());
		
		return instance;
	}

}
