package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.contact_consultation;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.ContactConsultation;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ContactConsultationDTO;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 *
 * @author André
 */
@Mapper
public interface ContactConsultationMapper {
	
	ContactConsultation contactConsultationDtoToContactConsultation(ContactConsultationDTO contactConsultationDTO);

	ContactConsultationDTO contactConsultationToContactConsultationDTO(ContactConsultation contactConsultation);

	List<ContactConsultationDTO> listContactConsultationToListContactConsultationDTO(List<ContactConsultation> contactConsultation);

	default Page<ContactConsultationDTO> contactConsultationPageToContactConsultationDtoPage(Page<ContactConsultation> contactConsultation, Pageable pageable) {

		List<ContactConsultationDTO> listContactConsultationDto = listContactConsultationToListContactConsultationDTO(contactConsultation.getContent());
		Page<ContactConsultationDTO> contactConsultationDTOPage = new PageImpl<>(listContactConsultationDto, pageable, contactConsultation.getTotalElements());
		return contactConsultationDTOPage;
	}
}
