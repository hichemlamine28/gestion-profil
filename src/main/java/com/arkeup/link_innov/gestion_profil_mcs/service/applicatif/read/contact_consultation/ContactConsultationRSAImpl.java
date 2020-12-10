package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.contact_consultation;


import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.contact_consultation.ContactConsultationMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ContactConsultationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.contact_consultation.ContactConsultationRSM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author André
 *
 */
@Service
public class ContactConsultationRSAImpl implements ContactConsultationRSA {


	@Autowired
	ContactConsultationRSM contactConsultationRSM;

	@Autowired
	ContactConsultationMapper contactConsultationMapper;

	@Override
	public ContactConsultationDTO findByUserId(String userId) {
		return contactConsultationMapper.contactConsultationToContactConsultationDTO(contactConsultationRSM.findByUserId(userId));
	}
}
