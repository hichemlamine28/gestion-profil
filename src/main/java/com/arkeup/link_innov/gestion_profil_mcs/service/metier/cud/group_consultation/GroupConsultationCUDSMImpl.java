package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.group_consultation;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.GroupConsultation;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.GroupConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author André
 *
 */
@Service
public class GroupConsultationCUDSMImpl implements GroupConsultationCUDSM {

    @Autowired
    GroupConsultationRepository groupConsultationRepository;

    @Override
    public GroupConsultation update(GroupConsultation groupConsultation) {
        return groupConsultationRepository.save(groupConsultation);
    }

}
