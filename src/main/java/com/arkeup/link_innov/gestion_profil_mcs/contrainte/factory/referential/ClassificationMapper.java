package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.referential;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Classification;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ClassificationDTO;

@Mapper
public interface ClassificationMapper {
	
	ClassificationDTO classificationToClassificationDTO(Classification classification);
	Classification classificationDTOToClassification(ClassificationDTO classificationDTO);
	List<ClassificationDTO> classificationToClassificationDTOs(List<Classification> classifications);
	List<Classification> classificationDTOToClassifications(List<ClassificationDTO> classificationDTOs);
	
	ClassificationMapper MAPPER = Mappers.getMapper(ClassificationMapper.class);
	
	default Page<ClassificationDTO> classificationPageToClassificationDTOPage(Page<Classification> classificationPage, Pageable pageable) {

		List<ClassificationDTO> classificationDtos = MAPPER.classificationToClassificationDTOs(classificationPage.getContent());
		Page<ClassificationDTO> classificationDTOPage = new PageImpl<>(classificationDtos, pageable, classificationPage.getTotalElements());
		return classificationDTOPage;
	}

}
