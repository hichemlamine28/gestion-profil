package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.PageContactsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

public class ReseauSocialUsersDTO extends BaseDTO {

	private PageContactsDTO contactDTOs;

	public PageContactsDTO getContactDTOs() {
		return contactDTOs;
	}

	public void setContactDTOs(PageContactsDTO contactDTOs) {
		this.contactDTOs = contactDTOs;
	}

}
