package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import java.util.Date;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.googlecode.jmapper.annotations.JGlobalMap;

/**
 *
 * @author bona
 */
@JGlobalMap
public class ParcoursDTO extends BaseDTO {

    private String id;

    private String profil;

    private String occupation;

    private String zipCode;

    private String lieu;

    private String description;

    private String photo;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date startDate;

    private Date endDate;

    private Date createDate;

    private Date modifDate;

    /**
     * Les informations sur employeur de l'utilisateur.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CorporationDTO company;

    /**
     * Les informations sur le type de contact (Académique, Industrielle ou
     * Autres)
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CategoryDTO category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifDate() {
        return modifDate;
    }

    public void setModifDate(Date modifDate) {
        this.modifDate = modifDate;
    }

    public CorporationDTO getCompany() {
        return company;
    }

    public void setCompany(CorporationDTO company) {
        this.company = company;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

}
