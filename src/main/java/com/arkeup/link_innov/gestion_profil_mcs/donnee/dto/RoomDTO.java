package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author bona
 */
public class RoomDTO {

    private String id;
    private String roomName;
    private List<String> members;
    private List<String> admins;
    private String accountID;
    private String roomType;
    private Date creationDate;
    private String description;
    private String subject;
    private List<String> removedAdmins;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    public List<String> getAdmins() {
        return admins;
    }

    public void setAdmins(List<String> admins) {
        this.admins = admins;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<String> getRemovedAdmins() {
        return removedAdmins;
    }

    public void setRemovedAdmins(List<String> removedAdmins) {
        this.removedAdmins = removedAdmins;
    }

}
