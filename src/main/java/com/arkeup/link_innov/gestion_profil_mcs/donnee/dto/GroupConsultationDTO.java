package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

import java.util.Date;

public class GroupConsultationDTO extends BaseDTO {

    private String id;

    private String groupId;

    private Date groupConsultationLastDate;

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
