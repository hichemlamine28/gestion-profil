package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.group_consultation;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.GroupConsultation;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.GroupConsultationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *
 * @author André
 */
@Mapper
public interface GroupConsultationMapper {
	
	GroupConsultation groupConsultationDtoToGroupConsultation(GroupConsultationDTO groupConsultationDTO);

	GroupConsultationDTO groupConsultationToGroupConsultationDTO(GroupConsultation groupConsultation);

	List<GroupConsultationDTO> listGroupConsultationToListGroupConsultationDTO(List<GroupConsultation> groupConsultation);

	default Page<GroupConsultationDTO> groupConsultationPageToGroupConsultationDtoPage(Page<GroupConsultation> groupConsultation, Pageable pageable) {

		List<GroupConsultationDTO> listGroupConsultationDto = listGroupConsultationToListGroupConsultationDTO(groupConsultation.getContent());
		Page<GroupConsultationDTO> groupConsultationDTOPage = new PageImpl<>(listGroupConsultationDto, pageable, groupConsultation.getTotalElements());
		return groupConsultationDTOPage;
	}
}
