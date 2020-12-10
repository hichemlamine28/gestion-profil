package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

/**
 *
 * @author bona
 */
public class UserValidatorDTO {

    private String id;
    private String type;
    private Boolean isCertifier;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getIsCertifier() {
        return isCertifier;
    }

    public void setIsCertifier(Boolean isCertifier) {
        this.isCertifier = isCertifier;
    }

}
