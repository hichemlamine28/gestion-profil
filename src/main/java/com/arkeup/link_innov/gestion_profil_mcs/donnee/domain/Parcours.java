package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author bona
 */
@org.springframework.data.mongodb.core.mapping.Document
@org.springframework.data.elasticsearch.annotations.Document(indexName = "gestion_profil_mcs", type = "Parcours")
public class Parcours {

    /**
     * Parcours identifier
     *
     * @type {string}
     * @memberof Parcours
     */
    @Id
    private String id;

    /**
     * id de l'utilisateur
     *
     * @type {string}
     * @memberof Parcours
     */
    private String profil;

    /**
     * occupation
     *
     * @type {string}
     * @memberof Parcours
     */
    private String occupation;

    /**
     * zip code
     *
     * @type {string}
     * @memberof Parcours
     */
    private String zipCode;

    /**
     * ville
     *
     * @type {string}
     * @memberof Parcours
     */
    private String lieu;

    /**
     * résumé
     *
     * @type {string}
     * @memberof Parcours
     */
    private String description;

    /**
     * photo
     *
     * @type {string}
     * @memberof Parcours
     */
    private String photo;

    /**
     * date debut
     *
     * @type {date}
     * @memberof Parcours
     */
    private Date startDate;

    /**
     * date fin
     *
     * @type {date}
     * @memberof Parcours
     */
    private Date endDate;

    /**
     * date creation
     *
     * @type {date}
     * @memberof Parcours
     */
    private Date createDate;

    /**
     * date modification
     *
     * @type {date}
     * @memberof Parcours
     */
    private Date modifDate;

    /**
     * entreprise
     *
     * @type {IdentityLabelPair<String, String>}
     * @memberof Parcours Les informations sur employeur de l'utilisateur.
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
   // @Field(type = FieldType.Nested)
    private Corporation company;

    /**
     * User category
     *
     * @type {IdentityLabelPair<String, String>}
     * @memberof Parcours Les informations sur le type de contact (Académique,
     * Industrielle ou Autres)
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
   // @Field(type = FieldType.Nested)
    private Category category;

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

    public Corporation getCompany() {
        return company;
    }

    public void setCompany(Corporation company) {
        this.company = company;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
