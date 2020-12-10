package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.production.patent;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Patent;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Productions;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.PatentDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProductionsDTO;

@Mapper
public interface PatentMapper {
	PatentDTO patentToPatentDTO(Patent patent);

	Patent patentDTOToPatent (PatentDTO patentDTO);
	List <PatentDTO> patentToPatentDTOs(List <Patent> patents);
	List <Patent> patentDTOToPatents (List <PatentDTO> patentDTOs);
	
	default Page<PatentDTO> patentPageToPatentDtoPage(Page<Patent> patentPage, Pageable pageable) {

		List<PatentDTO> patentDtos = patentToPatentDTOs(patentPage.getContent());
		Page<PatentDTO> patentDTOPage = new PageImpl<>(patentDtos, pageable, patentPage.getTotalElements());
		return patentDTOPage;
	}
	
	/*Must be declared for subclass*/
	ProductionsDTO productionsToProductionsDTO(Productions productions);

	Productions productionsDTOToProductions(ProductionsDTO productionsDTO);
}
