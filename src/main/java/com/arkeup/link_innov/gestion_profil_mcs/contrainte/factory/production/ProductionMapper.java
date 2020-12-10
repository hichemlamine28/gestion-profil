package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.production;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.production.other.OtherProductionMapper;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.production.patent.PatentMapper;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.production.project.ProjectMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.OtherProduction;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Patent;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Productions;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Project;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProductionsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.utils.HelpPage;

@Mapper
public interface ProductionMapper {
	
	ProductionMapper MAPPER = Mappers.getMapper(ProductionMapper.class);
	ProjectMapper PROJMAP = Mappers.getMapper(ProjectMapper.class);
	PatentMapper PATENTMAP = Mappers.getMapper(PatentMapper.class);
	OtherProductionMapper OTHERMAP = Mappers.getMapper(OtherProductionMapper.class);
	
	List<ProductionsDTO> productionToProductionDTOs(List<Productions> productions);

	List<Productions> productionDTOsToProduction(List<ProductionsDTO> productionsDTOS);

	default ProductionsDTO productionToProductionDTO(Productions production) {
		if (production instanceof Project)
			return PROJMAP.toDTO((Project)production);
		else if (production instanceof Patent)
			return PATENTMAP.patentToPatentDTO((Patent)production);
		else
			return OTHERMAP.otherProductionToOtherProductionDTO((OtherProduction)production);
		
	}	

	default Page<ProductionsDTO> productionPageToProductionDtoPage(
			Page<Productions> productionPage, Pageable pageable) {

		List<ProductionsDTO> productionDtos = MAPPER
				.productionToProductionDTOs(productionPage.getContent());
		return new PageImpl<>(productionDtos, pageable,
				productionPage.getTotalElements());
	}
	
	default HelpPage<ProductionsDTO> helpPageCorporationToHelpPageCorporationDTOs(HelpPage<Productions> productions){

		List<ProductionsDTO> productionsDTOs = this.productionToProductionDTOs(productions.getContent());
		
		return new HelpPage<>(productionsDTOs, productions.getPageable(),
				productions.getTotalElements());
		
	}

}
