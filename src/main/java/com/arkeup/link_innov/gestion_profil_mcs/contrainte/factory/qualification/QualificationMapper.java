package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.qualification;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Qualification;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.QualificationDTO;

@Mapper
public interface QualificationMapper {
	QualificationDTO qualificationToQualificationDTO(Qualification qualification);
	Qualification qualificationDTOToQualification (QualificationDTO qualificationDTO);
	List <QualificationDTO> qualificationToQualificationDTOs(List <Qualification> qualifications);
	List <Qualification> qualificationDTOToQualifications (List <QualificationDTO> qualificationDTOs);
	
	QualificationMapper MAPPER = Mappers.getMapper(QualificationMapper.class);
	
	default Page<QualificationDTO> qualificationPageToQualificationDtoPage(Page<Qualification> qualificationPage, Pageable pageable) {

		List<QualificationDTO> qualificationDtos = MAPPER.qualificationToQualificationDTOs(qualificationPage.getContent());
		Page<QualificationDTO> qualificationDTOPage = new PageImpl<>(qualificationDtos, pageable, qualificationPage.getTotalElements());
		return qualificationDTOPage;
	}
}
