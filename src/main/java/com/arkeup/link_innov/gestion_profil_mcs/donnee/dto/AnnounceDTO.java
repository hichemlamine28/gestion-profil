package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import java.util.Date;
import java.util.List;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AnnounceDTO extends BaseDTO {
	
	private String id;
	private String description;
    private Date createDate;
    private Date modifDate;
    private Date startDate;
    private Date expirationDate;
    private Date limitDate;
    @JsonProperty("announceTypology")
    private String typology;
    private String title;
    private List<String> keyword;
    private String url;
    private String state;
    private String announcer;
    private String corporationId;
    private String announcerInformation;
    private Boolean anonymous;
    private String proposition;
    private String type;
    private String zipCode;
    private String city;
    private List<String> skills;
    private MediaDTO mediaDTO;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public Date getLimitDate() {
		return limitDate;
	}
	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}
	public String getTypology() {
		return typology;
	}
	public void setTypology(String typology) {
		this.typology = typology;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getKeyword() {
		return keyword;
	}
	public void setKeyword(List<String> keyword) {
		this.keyword = keyword;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAnnouncer() {
		return announcer;
	}
	public void setAnnouncer(String announcer) {
		this.announcer = announcer;
	}
	public String getCorporationId() {
		return corporationId;
	}
	public void setCorporationId(String corporationId) {
		this.corporationId = corporationId;
	}
	public String getAnnouncerInformation() {
		return announcerInformation;
	}
	public void setAnnouncerInformation(String announcerInformation) {
		this.announcerInformation = announcerInformation;
	}
	public Boolean getAnonymous() {
		return anonymous;
	}
	public void setAnonymous(Boolean anonymous) {
		this.anonymous = anonymous;
	}
	public String getProposition() {
		return proposition;
	}
	public void setProposition(String proposition) {
		this.proposition = proposition;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public List<String> getSkills() {
		return skills;
	}
	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	public MediaDTO getMediaDTO() {
		return mediaDTO;
	}
	public void setMediaDTO(MediaDTO mediaDTO) {
		this.mediaDTO = mediaDTO;
	}
    
    
}
