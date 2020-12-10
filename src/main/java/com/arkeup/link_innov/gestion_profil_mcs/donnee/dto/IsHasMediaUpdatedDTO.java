package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class IsHasMediaUpdatedDTO {

	@JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isHasMediaProfilUpdated;

	@JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isHasMediaCorporationUpdated;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean isHasMediaGroupUpdated;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean isHasMediaProductionUpdated;

	public Boolean getHasMediaProfilUpdated() {
		return isHasMediaProfilUpdated;
	}

	public void setHasMediaProfilUpdated(Boolean hasMediaProfilUpdated) {
		isHasMediaProfilUpdated = hasMediaProfilUpdated;
	}

	public Boolean getHasMediaCorporationUpdated() {
		return isHasMediaCorporationUpdated;
	}

	public void setHasMediaCorporationUpdated(Boolean hasMediaCorporationUpdated) {
		isHasMediaCorporationUpdated = hasMediaCorporationUpdated;
	}

	public Boolean getHasMediaGroupUpdated() {
		return isHasMediaGroupUpdated;
	}

	public void setHasMediaGroupUpdated(Boolean hasMediaGroupUpdated) {
		isHasMediaGroupUpdated = hasMediaGroupUpdated;
	}

	public Boolean getHasMediaProductionUpdated() {
		return isHasMediaProductionUpdated;
	}

	public void setHasMediaProductionUpdated(Boolean hasMediaProductionUpdated) {
		isHasMediaProductionUpdated = hasMediaProductionUpdated;
	}
}
