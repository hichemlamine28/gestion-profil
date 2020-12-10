package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.production.other;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.OtherProduction;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Productions;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.OtherProductionDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProductionsDTO;

@Mapper
public interface OtherProductionMapper {

	OtherProductionDTO otherProductionToOtherProductionDTO(OtherProduction otherProduction);

	OtherProduction otherProductionDTOToOtherProduction(OtherProductionDTO otherProductionDTO);

	List<OtherProductionDTO> otherProductionToOtherProductionDTOs(List<OtherProduction> otherProductions);

	List<OtherProduction> otherProductionDTOToOtherProductions(List<OtherProductionDTO> otherProductionDTOs);

	default Page<OtherProductionDTO> otherProductionPageToOtherProductionDtoPage(
			Page<OtherProduction> otherProductionPage, Pageable pageable) {

		List<OtherProductionDTO> otherProductionDtos = otherProductionToOtherProductionDTOs(otherProductionPage.getContent());
		Page<OtherProductionDTO> otherProductionDTOPage = new PageImpl<>(otherProductionDtos, pageable,
				otherProductionPage.getTotalElements());
		return otherProductionDTOPage;
	}
	
	/*Must be declared for subclass*/
	ProductionsDTO productionsToProductionsDTO(Productions productions);

	Productions productionsDTOToProductions(ProductionsDTO productionsDTO);
}
