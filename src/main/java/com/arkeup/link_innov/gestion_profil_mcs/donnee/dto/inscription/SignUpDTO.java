/**
 *
 */
package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.inscription;

import java.util.List;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CategoryDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CorporationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserAuthDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ActivitySectorDTO;
import com.googlecode.jmapper.annotations.JGlobalMap;

/**
 * @author mikajy
 *
 * La regle de gestion RG19.1 a definit les propriétes à mettre dans ce DTO
 *
 */
@JGlobalMap
public class SignUpDTO extends UserAuthDTO {

    /**
     * Les informations sur employeur de l'utilisateur.
     */
    private CorporationDTO employer;
    /**
     * Les informations sur le type de contact (Académique, Industrielle ou
     * Autres)
     */
    private CategoryDTO type;

    private String phoneNumber;

    private String webSite;

    private Boolean male;

    private String profession;

    private Boolean hasMedia = false;

    private List<ActivitySectorDTO> activityArea;

    /**
     * @return the type
     */
    public CategoryDTO getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(CategoryDTO type) {
        this.type = type;
    }

    /**
     * @return the employer
     */
    public CorporationDTO getEmployer() {
        return employer;
    }

    /**
     * @param employer the employer to set
     */
    public void setEmployer(CorporationDTO employer) {
        this.employer = employer;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the webSite
     */
    public String getWebSite() {
        return webSite;
    }

    /**
     * @param webSite the webSite to set
     */
    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public Boolean getMale() {
        return male;
    }

    public void setMale(Boolean male) {
        this.male = male;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

	public List<ActivitySectorDTO> getActivityArea() {
		return activityArea;
	}

	public void setActivityArea(List<ActivitySectorDTO> activityArea) {
		this.activityArea = activityArea;
	}

    public Boolean getHasMedia() {
        return hasMedia;
    }

    public void setHasMedia(Boolean hasMedia) {
        this.hasMedia = hasMedia;
    }
}
