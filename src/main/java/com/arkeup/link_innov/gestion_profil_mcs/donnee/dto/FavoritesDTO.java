package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import org.springframework.data.domain.Page;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

public class FavoritesDTO extends BaseDTO {

	private Page<AnnounceDTO> announceDTOs;
	private Page<PostDTO> postDTOs;
	private Page<ProductionsDTO> productionDTOs;
	private Page<ProfilDTO> profilDTOs;
	private Page<CorporationDTO> corporationDTOs;
	private Page<GroupDTO> groupDTOs;

	public Page<AnnounceDTO> getAnnounceDTOs() {
		return announceDTOs;
	}

	public void setAnnounceDTOs(Page<AnnounceDTO> announceDTOs) {
		this.announceDTOs = announceDTOs;
	}

	public Page<PostDTO> getPostDTOs() {
		return postDTOs;
	}

	public void setPostDTOs(Page<PostDTO> postDTOs) {
		this.postDTOs = postDTOs;
	}

	public Page<ProductionsDTO> getProductionDTOs() {
		return productionDTOs;
	}

	public void setProductionDTOs(Page<ProductionsDTO> productionDTOs) {
		this.productionDTOs = productionDTOs;
	}

	public Page<ProfilDTO> getProfilDTOs() {
		return profilDTOs;
	}

	public void setProfilDTOs(Page<ProfilDTO> profilDTOs) {
		this.profilDTOs = profilDTOs;
	}

	public Page<CorporationDTO> getCorporationDTOs() {
		return corporationDTOs;
	}

	public void setCorporationDTOs(Page<CorporationDTO> corporationDTOs) {
		this.corporationDTOs = corporationDTOs;
	}

	public Page<GroupDTO> getGroupDTOs() {
		return groupDTOs;
	}

	public void setGroupDTOs(Page<GroupDTO> groupDTOs) {
		this.groupDTOs = groupDTOs;
	}
}
