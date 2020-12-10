package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate;

import java.util.Date;
import java.util.List;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

/**
 *
 * @author benja
 */
public class SubscriptionDTO extends BaseDTO {

    private String id;
    private Date creationDate;
    private Date modificationDate;
    private String displayName;
    private Boolean active = false;
    private Boolean state = false;
    private List<String> permissionList;
    private List<PermissionDTO> permissionDTOList;
    private String adminId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public List<String> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<String> permissionList) {
        this.permissionList = permissionList;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public List<PermissionDTO> getPermissionDTOList() {
        return permissionDTOList;
    }

    public void setPermissionDTOList(List<PermissionDTO> permissionDTOList) {
        this.permissionDTOList = permissionDTOList;
    }

}
