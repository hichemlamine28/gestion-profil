package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import java.util.List;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class GroupMembersRelationshipsDTO extends BaseDTO{
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<GroupMembersRelationshipDTO> listGroupMembersRelationshipDTO;

	public List<GroupMembersRelationshipDTO> getListGroupMembersRelationshipDTO() {
		return listGroupMembersRelationshipDTO;
	}

	public void setListGroupMembersRelationshipDTO(List<GroupMembersRelationshipDTO> listGroupMembersRelationshipDTO) {
		this.listGroupMembersRelationshipDTO = listGroupMembersRelationshipDTO;
	}
	
	

}
