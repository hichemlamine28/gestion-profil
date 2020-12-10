package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

import java.util.Date;

public class ContactConsultationDTO extends BaseDTO {

    private String id;

    private Date contactConsultationLastDate;

    private String userId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public Date getContactConsultationLastDate() {
        return contactConsultationLastDate;
    }

    public void setContactConsultationLastDate(Date contactConsultationLastDate) {
        this.contactConsultationLastDate = contactConsultationLastDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
