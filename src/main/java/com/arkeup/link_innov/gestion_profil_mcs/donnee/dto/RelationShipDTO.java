package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import com.googlecode.jmapper.annotations.JGlobalMap;

/**
 *
 * @author bona
 */
@JGlobalMap
public class RelationShipDTO {

	private String idRequest;
    private Boolean isContact;
    private Boolean isFollower;
    private Boolean isPending;

    public Boolean getIsContact() {
        return isContact;
    }

    public void setIsContact(Boolean isContact) {
        this.isContact = isContact;
    }

    public Boolean getIsFollower() {
        return isFollower;
    }

    public void setIsFollower(Boolean isFollower) {
        this.isFollower = isFollower;
    }

    public Boolean getIsPending() {
        return isPending;
    }

    public void setIsPending(Boolean isPending) {
        this.isPending = isPending;
    }

	public String getIdRequest() {
		return idRequest;
	}

	public void setIdRequest(String idRequest) {
		this.idRequest = idRequest;
	}
    
    
}
