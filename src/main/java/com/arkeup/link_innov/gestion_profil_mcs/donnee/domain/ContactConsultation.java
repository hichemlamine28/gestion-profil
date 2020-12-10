package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain;

import org.springframework.data.annotation.Id;

import java.util.Date;

@org.springframework.data.mongodb.core.mapping.Document
@org.springframework.data.elasticsearch.annotations.Document(indexName = "gestion_profil_mcs", type = "ContactConsultation")
public class ContactConsultation {

    @Id
    private String id;

    /**
     * Consultation contact last date
     *
     * @type {date}
     * @memberof ContactConsultation
     */
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
