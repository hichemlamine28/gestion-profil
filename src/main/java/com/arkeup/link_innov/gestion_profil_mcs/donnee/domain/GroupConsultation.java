package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain;

import org.springframework.data.annotation.Id;

import java.util.Date;

@org.springframework.data.mongodb.core.mapping.Document
@org.springframework.data.elasticsearch.annotations.Document(indexName = "gestion_profil_mcs", type = "GroupConsultation")
public class GroupConsultation {

    @Id
    private String id;

    /**
     * Group Id
     *
     * @type {string}
     * @memberof GroupConsultation
     */
    private String groupId;
    /**
     * Consultation group last date
     *
     * @type {date}
     * @memberof GroupConsultation
     */
    private Date groupConsultationLastDate;

    /**
     * User group Id
     *
     * @type {string}
     * @memberof GroupConsultation
     */
    private String userGroupId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Date getGroupConsultationLastDate() {
        return groupConsultationLastDate;
    }

    public void setGroupConsultationLastDate(Date groupConsultationLastDate) {
        this.groupConsultationLastDate = groupConsultationLastDate;
    }

    public String getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(String userGroupId) {
        this.userGroupId = userGroupId;
    }
}
