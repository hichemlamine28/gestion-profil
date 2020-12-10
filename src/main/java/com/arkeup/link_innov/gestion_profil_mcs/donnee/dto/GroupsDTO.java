package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import org.springframework.data.domain.Page;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

public class GroupsDTO extends BaseDTO {
	
	Page<GroupDTO> groupDTOs;

	public Page<GroupDTO> getGroupDTOs() {
		return groupDTOs;
	}

	public void setGroupDTOs(Page<GroupDTO> groupDTOs) {
		this.groupDTOs = groupDTOs;
	}

}
