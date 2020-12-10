package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class IsFollowerDTO extends BaseDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Boolean isFollower = false;

	public Boolean getIsFollower() {
		return isFollower;
	}

	public void setIsFollower(Boolean isFollower) {
		this.isFollower = isFollower;
	}

}
