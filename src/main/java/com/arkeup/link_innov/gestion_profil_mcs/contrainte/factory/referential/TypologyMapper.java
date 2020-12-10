package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.referential;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Typology;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.TypologyDTO;

@Mapper
public interface TypologyMapper {
	TypologyDTO typologyToTypologyDTO(Typology typology);
	Typology typologyDTOToTypology(TypologyDTO typologyDTO);
	List<TypologyDTO> typologyToTypologyDTOs(List<Typology> typologys);
	List<Typology> typologyDTOToTypologys(List<TypologyDTO> typologyDTOs);
	
	TypologyMapper MAPPER = Mappers.getMapper(TypologyMapper.class);
	
	default Page<TypologyDTO> typologyPageToTypologyDTOPage(Page<Typology> typologyPage, Pageable pageable) {

		List<TypologyDTO> typologyDtos = MAPPER.typologyToTypologyDTOs(typologyPage.getContent());
		Page<TypologyDTO> typologyDTOPage = new PageImpl<>(typologyDtos, pageable, typologyPage.getTotalElements());
		return typologyDTOPage;
	}
}
