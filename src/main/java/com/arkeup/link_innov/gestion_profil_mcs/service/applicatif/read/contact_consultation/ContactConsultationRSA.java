package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.contact_consultation;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ContactConsultationDTO;

/**
 *
 * @author André
 *
 */
public interface ContactConsultationRSA {


	ContactConsultationDTO findByUserId(String userId);

}
