package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.contact_consultation;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.ContactConsultation;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ContactConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author André
 */
@Service
public class ContactConsultationRSMImpl implements ContactConsultationRSM {

	@Autowired
	ContactConsultationRepository contactConsultationRepository;

	@Override
	public ContactConsultation findByUserId(String userId) {
		 return contactConsultationRepository.findByUserId(userId).stream().findFirst().orElse(null);
	}
}
