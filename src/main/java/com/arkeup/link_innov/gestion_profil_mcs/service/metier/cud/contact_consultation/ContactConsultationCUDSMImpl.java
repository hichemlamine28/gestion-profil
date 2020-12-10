package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.contact_consultation;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.ContactConsultation;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.contact_consultation.ContactConsultationCUDSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ContactConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author André
 *
 */
@Service
public class ContactConsultationCUDSMImpl implements ContactConsultationCUDSM {

    @Autowired
    ContactConsultationRepository contactConsultationRepository;

    @Override
    public ContactConsultation update(ContactConsultation contactConsultation) {
        return contactConsultationRepository.save(contactConsultation);
    }

}
