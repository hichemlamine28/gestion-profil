package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import java.util.Date;
import java.util.List;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.DownloadStatus;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ActivitySectorDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.googlecode.jmapper.annotations.JGlobalMap;

@JGlobalMap
public class ProfilDTO extends BaseDTO {

	private String id;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String username;

	private String firstname;

	private String chatId;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String lastname;

	private String occupation;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String zipCode;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String city;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String webSite;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String phoneNumber;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String email;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String pseudoName;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String resume;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String photo;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean isProfileInitialised;

	private List<ActivitySectorDTO> activityArea;

	private int percentage;


	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean shouldFetchAll = false;
	
	/**
	 * Les informations sur employeur de l'utilisateur.
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private CorporationDTO company;

	/**
	 * Les informations sur le type de contact (Académique, Industrielle ou Autres)
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private CategoryDTO category;

	private RelationShipDTO relationShip;

	/**
	 * civilité de l'utilisateur.
	 */
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean male;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private MediaDTO mediaDTO;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String mediaId;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private MediaDTO backgroundDTO;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String backgroundId;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private MediaDTO exportDTO;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String exportId;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private DownloadStatus downloadStatus = DownloadStatus.NONE;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date creationDate;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String domaine;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<String> langages;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean onBording = false;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String keyValidateProfil;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Date expirationKeyValidateProfil;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean isActiveAccount = false;

	private String subscriptionName;

	private Boolean contactConsultation;

	private Date contactConsultationLastDate;

	private Boolean hasMedia = false;

	private Boolean hasBackground = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPseudoName() {
		return pseudoName;
	}

	public void setPseudoName(String pseudoName) {
		this.pseudoName = pseudoName;
	}

	public RelationShipDTO getRelationShip() {
		return relationShip;
	}

	public void setRelationShip(RelationShipDTO relationShip) {
		this.relationShip = relationShip;
	}

	/**
	 * @return the chatId
	 */
	public String getChatId() {
		return chatId;
	}

	/**
	 * @param chatId the chatId to set
	 */
	public void setChatId(String chatId) {
		this.chatId = chatId;
	}

	public Boolean getMale() {
		return male;
	}

	public void setMale(Boolean male) {
		this.male = male;
	}

	public MediaDTO getMediaDTO() {
		return mediaDTO;
	}

	public void setMediaDTO(MediaDTO mediaDTO) {
		this.mediaDTO = mediaDTO;
	}

	public Boolean getIsProfileInitialised() {
		return isProfileInitialised;
	}

	public void setIsProfileInitialised(Boolean isProfileInitialised) {
		this.isProfileInitialised = isProfileInitialised;
	}

	public MediaDTO getBackgroundDTO() {
		return backgroundDTO;
	}

	public void setBackgroundDTO(MediaDTO backgroundDTO) {
		this.backgroundDTO = backgroundDTO;
	}

	public MediaDTO getExportDTO() {
		return exportDTO;
	}

	public void setExportDTO(MediaDTO exportDTO) {
		this.exportDTO = exportDTO;
	}

	public DownloadStatus getDownloadStatus() {
		return downloadStatus;
	}

	public void setDownloadStatus(DownloadStatus downloadStatus) {
		this.downloadStatus = downloadStatus;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public List<ActivitySectorDTO> getActivityArea() {
		return activityArea;
	}

	public void setActivityArea(List<ActivitySectorDTO> activityArea) {
		this.activityArea = activityArea;
	}

	public String getDomaine() {
		return domaine;
	}

	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}

	public List<String> getLangages() {
		return langages;
	}

	public void setLangages(List<String> langages) {
		this.langages = langages;
	}

	public Boolean getOnBording() {
		return onBording;
	}

	public void setOnBording(Boolean onBording) {
		this.onBording = onBording;
	}

	public String getKeyValidateProfil() {
		return keyValidateProfil;
	}

	public void setKeyValidateProfil(String keyValidateProfil) {
		this.keyValidateProfil = keyValidateProfil;
	}

	public Date getExpirationKeyValidateProfil() {
		return expirationKeyValidateProfil;
	}

	public void setExpirationKeyValidateProfil(Date expirationKeyValidateProfil) {
		this.expirationKeyValidateProfil = expirationKeyValidateProfil;
	}

	public Boolean getIsActiveAccount() {
		return isActiveAccount;
	}

	public void setIsActiveAccount(Boolean isActiveAccount) {
		this.isActiveAccount = isActiveAccount;
	}

	public String getSubscriptionName() {
		return subscriptionName;
	}

	public void setSubscriptionName(String subscriptionName) {
		this.subscriptionName = subscriptionName;
	}

	public Date getContactConsultationLastDate() {
		return contactConsultationLastDate;
	}

	public void setContactConsultationLastDate(Date contactConsultationLastDate) {
		this.contactConsultationLastDate = contactConsultationLastDate;
	}

	public Boolean getContactConsultation() {
		return contactConsultation;
	}

	public void setContactConsultation(Boolean contactConsultation) {
		this.contactConsultation = contactConsultation;
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

	public String getExportId() {
		return exportId;
	}

	public void setExportId(String exportId) {
		this.exportId = exportId;
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

	public int getPorcentage() {
		return percentage;
	}

	public void setPorcentage(int porcentage) {
		this.percentage = porcentage;
	}

	public Boolean getShouldFetchAll() {
		return shouldFetchAll;
	}

	public void setShouldFetchAll(Boolean shouldFetchAll) {
		this.shouldFetchAll = shouldFetchAll;
	}

}
