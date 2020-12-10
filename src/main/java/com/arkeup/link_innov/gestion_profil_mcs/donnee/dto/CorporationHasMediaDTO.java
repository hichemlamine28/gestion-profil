package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

public class CorporationHasMediaDTO {

    String corporationId;
    Boolean hasMedia;
    String type;

    public String getCorporationId() {
        return corporationId;
    }

    public void setCorporationId(String corporationId) {
        this.corporationId = corporationId;
    }

    public Boolean getHasMedia() {
        return hasMedia;
    }

    public void setHasMedia(Boolean hasMedia) {
        this.hasMedia = hasMedia;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
