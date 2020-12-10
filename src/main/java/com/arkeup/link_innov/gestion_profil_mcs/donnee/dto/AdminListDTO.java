package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import java.util.List;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

public class AdminListDTO extends BaseDTO{
	
	private String id;
	private List<String> admins;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getAdmins() {
		return admins;
	}
	public void setAdmins(List<String> admins) {
		this.admins = admins;
	}

}
