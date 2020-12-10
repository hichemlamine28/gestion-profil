package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import java.util.Date;
import java.util.List;


/**
 *
 * @author benja
 */
public class ActiveSubscriptionDTO extends BaseDTO {

    private String id;
    private Date creationDate;
    private Date modificationDate;
    private Boolean autoRenew = false;
    private String idSubscription;
    private String type;
    private List<String> permissionList;
    private String idUser;

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

    public Boolean getAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(Boolean autoRenew) {
        this.autoRenew = autoRenew;
    }

    public String getIdSubscription() {
        return idSubscription;
    }

    public void setIdSubscription(String idSubscription) {
        this.idSubscription = idSubscription;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<String> permissionList) {
        this.permissionList = permissionList;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

}
