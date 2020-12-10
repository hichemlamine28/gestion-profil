package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.referential;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.ThematicArea;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ThematicAreaDTO;

@Mapper
public interface ThematicAreaMapper {
	ThematicAreaDTO thematicAreaToThematicAreaDTO(ThematicArea thematicArea);
	ThematicArea thematicAreaDTOToThematicArea(ThematicAreaDTO thematicAreaDTO);
	List<ThematicAreaDTO> thematicAreaToThematicAreaDTOs(List<ThematicArea> thematicAreas);
	List<ThematicArea> thematicAreaDTOToThematicAreas(List<ThematicAreaDTO> thematicAreaDTOs);
	
	ThematicAreaMapper MAPPER = Mappers.getMapper(ThematicAreaMapper.class);
	
	default Page<ThematicAreaDTO> thematicAreaPageToThematicAreaDTOPage(Page<ThematicArea> thematicAreaPage, Pageable pageable) {

		List<ThematicAreaDTO> thematicAreaDtos = MAPPER.thematicAreaToThematicAreaDTOs(thematicAreaPage.getContent());
		Page<ThematicAreaDTO> thematicAreaDTOPage = new PageImpl<>(thematicAreaDtos, pageable, thematicAreaPage.getTotalElements());
		return thematicAreaDTOPage;
	}
}
