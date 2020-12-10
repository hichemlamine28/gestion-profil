package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.DownloadStatus;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.constants.UserType;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.ActivitySector;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;

@org.springframework.data.mongodb.core.mapping.Document
@org.springframework.data.elasticsearch.annotations.Document(indexName = "gestion_profil_mcs", type = "Profil")
public class Profil extends BaseDTO {

	@Id
	private String id;

	@Indexed(unique = true)
	private String username;
	/**
	 * Openfire id - le même que {@link UserAuth#getPseudoName())
	 */
	private String chatId;

	private String firstname;

	private String lastname;

	private String occupation;

	private String zipCode;

	private String city;

	private String webSite;

	private String phoneNumber;

	@Indexed(unique = true)
	private String email;

	private String resume;

	private UserType type;

	private Date lastConnectionDate;

	private Boolean isProfileInitialised = true;

	private List<ActivitySector> activityArea;

	private List<String> langages;

	private Boolean onBording = false;

	private String keyValidateProfil;

	private Date expirationKeyValidateProfil;

	private Boolean isActiveAccount = false;

	/**
	 * photo
	 *
	 * @type {string}
	 * @memberof UserInformation
	 */
	private String photo;

	/**
	 * entreprise
	 *
	 * @type {IdentityLabelPair<String, String>}
	 * @memberof Parcours Les informations sur employeur de l'utilisateur.
	 */
	// @Field(type = FieldType.Nested)
	private Corporation company;

	/**
	 * User category
	 *
	 * @type {IdentityLabelPair<String, String>}
	 * @memberof Parcours Les informations sur le type de contact (Académique,
	 *           Industrielle ou Autres)
	 */
	// @Field(type = FieldType.Nested)
	private Category category;

	private Boolean male;

	private String mediaId;

	private String backgroundId;

	private String exportId;

	private DownloadStatus downloadStatus = DownloadStatus.NONE;

	private Date creationDate;

	private String domaine;

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

	public String getChatId() {
		return chatId;
	}

	public void setChatId(String chatId) {
		this.chatId = chatId;
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

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public Date getLastConnectionDate() {
		return lastConnectionDate;
	}

	public void setLastConnectionDate(Date lastConnectionDate) {
		this.lastConnectionDate = lastConnectionDate;
	}

	public Boolean getIsProfileInitialised() {
		return isProfileInitialised;
	}

	public void setIsProfileInitialised(Boolean isProfileInitialised) {
		this.isProfileInitialised = isProfileInitialised;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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

	public Boolean getMale() {
		return male;
	}

	public void setMale(Boolean male) {
		this.male = male;
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

	public List<ActivitySector> getActivityArea() {
		return activityArea;
	}

	public void setActivityArea(List<ActivitySector> activityArea) {
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
