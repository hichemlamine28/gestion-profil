package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.corporation;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Corporation;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CorporationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.utils.HelpPage;

@Mapper
public interface CorporationMapper {

	CorporationDTO corporationToCorporationDTO(Corporation corporation);

	Corporation corporationDTOToCorporation(CorporationDTO corporationDTO);

	List<CorporationDTO> corporationToCorporationDTOs(List<Corporation> corporations);

	List<Corporation> corporationDTOToCorporations(List<CorporationDTO> corporationDTOs);

	CorporationMapper MAPPER = Mappers.getMapper(CorporationMapper.class);

	default Page<CorporationDTO> corporationPageToCorporationDtoPage(Page<Corporation> corporationPage,
			Pageable pageable) {

		List<CorporationDTO> corporationDtos = MAPPER.corporationToCorporationDTOs(corporationPage.getContent());
		Page<CorporationDTO> corporationDTOPage = new PageImpl<>(corporationDtos, pageable,
				corporationPage.getTotalElements());
		return corporationDTOPage;
	}
	
	default HelpPage<CorporationDTO> helpPageCorporationToHelpPageCorporationDTOs(HelpPage<Corporation> corporations){

		List<CorporationDTO> corporationDtos = this.corporationToCorporationDTOs(corporations.getContent());
		
		HelpPage<CorporationDTO> helpPageCorporationDTO = new HelpPage<>(corporationDtos, corporations.getPageable(),
				corporations.getTotalElements());
		
		return helpPageCorporationDTO;
	}
	
	
}
