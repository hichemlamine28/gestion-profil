package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.Date;

/**
 *
 * @author bona
 */
public class PermissionDTO extends BaseDTO {

    private String id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String displayName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date creationDate;

    private Date modificationDate;

    private int maxLimit;

    private Boolean permMounth;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
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

    public int getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(int maxLimit) {
        this.maxLimit = maxLimit;
    }

    public Boolean getPermMounth() {
        return permMounth;
    }

    public void setPermMounth(Boolean permMounth) {
        this.permMounth = permMounth;
    }

}
