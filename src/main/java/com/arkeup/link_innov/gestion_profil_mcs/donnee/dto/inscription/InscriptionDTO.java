/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.inscription;

import java.util.List;
import java.util.Objects;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CategoryDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.CorporationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.UserAuthDTO;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ActivitySectorDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author benja
 *
 */
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class InscriptionDTO extends UserAuthDTO {
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private CorporationDTO employer;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private CategoryDTO type;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String phoneNumber;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String webSite;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean male = true;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String profession;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<ActivitySectorDTO> activityArea;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String zipCode;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String city;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String resume;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<String> guestUsers;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String domaine;
	private Boolean hasMedia = false;

	public CorporationDTO getEmployer() {
		return employer;
	}

	public void setEmployer(CorporationDTO employer) {
		this.employer = employer;
	}

	public CategoryDTO getType() {
		return type;
	}

	public void setType(CategoryDTO type) {
		this.type = type;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getWebSite() {
		return webSite;
	}

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

	public List<String> getGuestUsers() {
		return guestUsers;
	}

	public void setGuestUsers(List<String> guestUsers) {
		this.guestUsers = guestUsers;
	}

	public String getDomaine() {
		return domaine;
	}

	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}

	public Boolean getHasMedia() {
		return hasMedia;
	}

	public void setHasMedia(Boolean hasMedia) {
		this.hasMedia = hasMedia;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof InscriptionDTO) {
			if (!super.equals(object)) {
				return false;
			}
			InscriptionDTO that = (InscriptionDTO) object;
			return Objects.equals(this.activityArea, that.activityArea) && Objects.equals(this.city, that.city)
					&& Objects.equals(this.employer, that.employer) && Objects.equals(this.male, that.male)
					&& Objects.equals(this.phoneNumber, that.phoneNumber)
					&& Objects.equals(this.profession, that.profession) && Objects.equals(this.resume, that.resume)
					&& Objects.equals(this.type, that.type) && Objects.equals(this.webSite, that.webSite)
					&& Objects.equals(this.zipCode, that.zipCode) && Objects.equals(this.guestUsers, that.guestUsers)
					&& Objects.equals(this.hasMedia, that.hasMedia) && Objects.equals(this.hasMedia, that.hasMedia)
					&& Objects.equals(this.domaine, that.domaine);
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), employer, activityArea, city, male, phoneNumber, profession, resume, type,
				webSite, zipCode, guestUsers, domaine);
	}
}
