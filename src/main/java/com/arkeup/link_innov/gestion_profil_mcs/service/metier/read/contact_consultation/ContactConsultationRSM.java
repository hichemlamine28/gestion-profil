package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.contact_consultation;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.ContactConsultation;

/**
 *
 * @author André
 */
public interface ContactConsultationRSM {

	ContactConsultation findByUserId(String userId);
}
