package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import org.springframework.data.domain.Page;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

public class AnnouncesDTO extends BaseDTO {
	Page<AnnounceDTO> announceDTOs;

	public Page<AnnounceDTO> getAnnounceDTOs() {
		return announceDTOs;
	}

	public void setAnnounceDTOs(Page<AnnounceDTO> announceDTOs) {
		this.announceDTOs = announceDTOs;
	}
}
