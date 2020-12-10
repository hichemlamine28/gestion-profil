package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.ActivitySector;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Classification;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Keyword;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.NumberOfEmployee;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.ThematicArea;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Typology;
import com.fasterxml.jackson.annotation.JsonProperty;

@org.springframework.data.mongodb.core.mapping.Document
@org.springframework.data.elasticsearch.annotations.Document(indexName = "gestion_profil_mcs", type = "Corporation")
public class Corporation {

	@Id
	private String id;

	/**
	 * Administrators
	 *
	 * @type {array}
	 * @memberof Corporation
	 */
	private List<String> admins;

	/**
	 * Administrators
	 *
	 * @type {array}
	 * @memberof Corporation
	 */
	private List<String> adminsChatId;

	/**
	 * Super Administrator
	 *
	 * @type {string}
	 * @memberof Corporation
	 */
	private String superAdmin;

	/**
	 * type
	 *
	 * @type {string}
	 * @memberof Corporation
	 */
	private Category type;

	/**
	 * Description
	 *
	 * @type {string}
	 * @memberof Corporation
	 */
	private String description;

	/**
	 * keywords
	 *
	 * @type {array}
	 * @memberof Corporation
	 */
	private List<Keyword> keywords;

	/**
	 * number of employees
	 *
	 * @type {int}
	 * @memberof Corporation
	 */
	private NumberOfEmployee employeesNumber;

	/**
	 * Activity area
	 *
	 * @type {string}
	 * @memberof Corporation
	 */
	private ActivitySector activityArea;

	/**
	 * Siret
	 *
	 * @type {string}
	 * @memberof Corporation
	 */
	private String siret;

	/**
	 * Name
	 *
	 * @type {string}
	 * @memberof Corporation
	 */
	@JsonProperty("corporationName")
	private String name;

	/**
	 * Structure
	 *
	 * @type {string}
	 * @memberof Corporation
	 */
	private String structure;

	/**
	 * Typology
	 *
	 * @type {string}
	 * @memberof Corporation
	 */
	private Typology typology;

	/**
	 * Classification
	 *
	 * @type {string}
	 * @memberof Corporation
	 */
	private Classification classification;

	/**
	 * Address
	 *
	 * @type {string}
	 * @memberof Corporation
	 */
	private String address;

	/**
	 * Web site
	 *
	 * @type {string}
	 * @memberof Corporation
	 */
	private String webSite;

	/**
	 * Photo
	 *
	 * @type {string}
	 * @memberof Corporation
	 */
	private String photo;

	/**
	 * Date of creation
	 *
	 * @type {date}
	 * @memberof Qualification
	 */
	private Date createDate;

	/**
	 * Date of modification
	 *
	 * @type {date}
	 * @memberof Qualification
	 */
	private Date modifDate;

	/**
	 * Thematic area
	 *
	 * @type {string}
	 * @memberof Corporation
	 */
	private ThematicArea thematicArea;

	private Integer certificationQuota;

	private Integer premiumAccountQuota;

	private String mediaId;
	
	private String backgroundId;

	private Boolean hasMedia = false;

	private Boolean hasBackground = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getAdmins() {
		return admins;
	}

	public void setAdmins(List<String> admins) {
		this.admins = admins;
	}

	public String getSuperAdmin() {
		return superAdmin;
	}

	public void setSuperAdmin(String superAdmin) {
		this.superAdmin = superAdmin;
	}

	public Category getType() {
		return type;
	}

	public void setType(Category type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Keyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<Keyword> keywords) {
		this.keywords = keywords;
	}

	public NumberOfEmployee getEmployeesNumber() {
		return employeesNumber;
	}

	public void setEmployeesNumber(NumberOfEmployee employeesNumber) {
		this.employeesNumber = employeesNumber;
	}

	public ActivitySector getActivityArea() {
		return activityArea;
	}

	public void setActivityArea(ActivitySector activityArea) {
		this.activityArea = activityArea;
	}

	public String getSiret() {
		return siret;
	}

	public void setSiret(String siret) {
		this.siret = siret;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStructure() {
		return structure;
	}

	public void setStructure(String structure) {
		this.structure = structure;
	}

	public Typology getTypology() {
		return typology;
	}

	public void setTypology(Typology typology) {
		this.typology = typology;
	}

	public Classification getClassification() {
		return classification;
	}

	public void setClassification(Classification classification) {
		this.classification = classification;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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

	public ThematicArea getThematicArea() {
		return thematicArea;
	}

	public void setThematicArea(ThematicArea thematicArea) {
		this.thematicArea = thematicArea;
	}

	public Integer getCertificationQuota() {
		return certificationQuota;
	}

	public void setCertificationQuota(Integer certificationQuota) {
		this.certificationQuota = certificationQuota;
	}

	public Integer getPremiumAccountQuota() {
		return premiumAccountQuota;
	}

	public void setPremiumAccountQuota(Integer premiumAccountQuota) {
		this.premiumAccountQuota = premiumAccountQuota;
	}

	public List<String> getAdminsChatId() {
		return adminsChatId;
	}

	public void setAdminsChatId(List<String> adminsChatId) {
		this.adminsChatId = adminsChatId;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getBackgroundId() {
		return backgroundId;
	}

	public void setBackgroundId(String backgroundId) {
		this.backgroundId = backgroundId;
	}

	public Boolean getHasMedia() {
		return hasMedia;
	}

	public void setHasMedia(Boolean hasMedia) {
		this.hasMedia = hasMedia;
	}

	public Boolean getHasBackground() {
		return hasBackground;
	}

	public void setHasBackground(Boolean hasBackground) {
		this.hasBackground = hasBackground;
	}
}
