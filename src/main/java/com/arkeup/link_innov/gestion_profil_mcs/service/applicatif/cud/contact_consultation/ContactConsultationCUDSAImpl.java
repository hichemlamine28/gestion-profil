package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.contact_consultation;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjetNotFoundException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.contact_consultation.ContactConsultationMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ContactConsultationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.ProfilDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.contact_consultation.ContactConsultationRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.profil.ProfilRSA;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.contact_consultation.ContactConsultationCUDSM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * @author André
 */
@Service
public class ContactConsultationCUDSAImpl implements ContactConsultationCUDSA {

    @Autowired
    ContactConsultationCUDSM contactConsultationCUDSM;

    @Autowired
    ContactConsultationRSA contactConsultationRSA;

    @Autowired
    ContactConsultationMapper contactConsultationMapper;

    @Autowired
    ProfilRSA profilRSA;

    @Override
    public ProfilDTO updateContactConsultation(String userId) {
        if(!StringUtils.isEmpty(userId)){
            ContactConsultationDTO contactConsultationDTO = contactConsultationRSA.findByUserId(userId);
            if(contactConsultationDTO == null){
                contactConsultationDTO = new ContactConsultationDTO();
            }
            contactConsultationDTO.setUserId(userId);
            contactConsultationDTO.setContactConsultationLastDate(new Date());
            contactConsultationCUDSM.update(contactConsultationMapper.contactConsultationDtoToContactConsultation(contactConsultationDTO));
            return profilRSA.getProfilByUserName(userId);
        }
        throw new ObjetNotFoundException(new ProfilDTO(), ErrorsEnum.ERR_MCS_PROFIL_0024);
    }

}
