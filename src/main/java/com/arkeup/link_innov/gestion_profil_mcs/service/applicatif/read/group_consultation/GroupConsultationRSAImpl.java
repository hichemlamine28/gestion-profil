package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.group_consultation;


import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.group_consultation.GroupConsultationMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.GroupConsultationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.group_consultation.GroupConsultationRSM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author André
 *
 */
@Service
public class GroupConsultationRSAImpl implements GroupConsultationRSA {


	@Autowired
	GroupConsultationRSM groupConsultationRSM;

	@Autowired
	GroupConsultationMapper groupConsultationMapper;

	@Override
	public GroupConsultationDTO findByGroupIdAndUserGroupId(String idGroup, String userGroupId) {
		return groupConsultationMapper.groupConsultationToGroupConsultationDTO(groupConsultationRSM.findByGroupIdAndUserGroupId(idGroup, userGroupId));
	}
}
