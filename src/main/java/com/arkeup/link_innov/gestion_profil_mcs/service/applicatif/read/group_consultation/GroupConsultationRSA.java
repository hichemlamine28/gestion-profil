package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.group_consultation;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.GroupConsultationDTO;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author André
 *
 */
public interface GroupConsultationRSA {


	GroupConsultationDTO findByGroupIdAndUserGroupId(String idGroup, String userGroupId);

}
