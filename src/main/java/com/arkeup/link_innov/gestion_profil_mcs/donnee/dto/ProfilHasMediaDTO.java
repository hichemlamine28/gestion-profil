package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

public class ProfilHasMediaDTO {

    String profilId;
    Boolean hasMedia;
    String type;

    public String getProfilId() {
        return profilId;
    }

    public void setProfilId(String profilId) {
        this.profilId = profilId;
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
