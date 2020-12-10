package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.profil.ProfilMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Profil;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CustomPageableDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.PageContactsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.ReseauSocialUserDTO;

/**
 * 
 * @author Jéremy
 *
 */
@Component
public class PageContactsDTOFactoryImpl implements PageContactsDTOFactory {

	@Autowired
	ProfilMapper profilMapper;

	@Autowired
	CustomPageableDTOFactory customPageableDTOFactory;

	@Override
	public PageContactsDTO getInstance() {
		return new PageContactsDTO();
	}

	@Override
	public PageContactsDTO getInstance(Page<Profil> pageProfils) {
		PageContactsDTO instance = getInstance();

		List<ReseauSocialUserDTO> contactsDTO = profilMapper.profilsToContactDTOs(pageProfils.getContent());
		CustomPageableDTO customPageableDTO = customPageableDTOFactory.getInstance(pageProfils.getPageable());

		instance.setContent(contactsDTO);
		instance.setPageable(customPageableDTO);
		instance.setTotalPages(pageProfils.getTotalPages());
		instance.setTotalElements(pageProfils.getTotalElements());
		instance.setLast(pageProfils.isLast());
		instance.setNumberOfElements(pageProfils.getNumberOfElements());
		instance.setSize(pageProfils.getSize());
		instance.setNumber(pageProfils.getNumber());
		instance.setFirst(pageProfils.isFirst());

		return instance;
	}

}
