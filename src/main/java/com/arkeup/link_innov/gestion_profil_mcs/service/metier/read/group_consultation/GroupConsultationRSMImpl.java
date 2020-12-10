package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.group_consultation;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.GroupConsultation;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.GroupConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author André
 */
@Service
public class GroupConsultationRSMImpl implements GroupConsultationRSM {

	@Autowired
	GroupConsultationRepository groupConsultationRepository;

	@Override
	public GroupConsultation findByGroupIdAndUserGroupId(String groupId, String userGroupId) {
		 List<GroupConsultation> groupConsultation = groupConsultationRepository.findByGroupIdAndUserGroupId(groupId, userGroupId);
		 if(groupConsultation.isEmpty())
		 {
		 	return null;
		 }
		 else
		 {
		 	return groupConsultation.get(0);
		 }
	}
}
