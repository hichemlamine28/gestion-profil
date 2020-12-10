package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.group_consultation;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.GroupConsultation;
/**
 *
 * @author André
 */
public interface GroupConsultationRSM {

	GroupConsultation findByGroupIdAndUserGroupId(String groupId, String userGroupId);
}
