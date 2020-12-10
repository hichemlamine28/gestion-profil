package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.googlecode.jmapper.annotations.JGlobalMap;

@JGlobalMap
public class PublicProfilDTO extends BaseDTO {

	private String id;

	private String firstname;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String lastname;

	private String occupation;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String resume;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private MediaDTO mediaDTO;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private MediaDTO backgroundDTO;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public MediaDTO getMediaDTO() {
		return mediaDTO;
	}

	public void setMediaDTO(MediaDTO mediaDTO) {
		this.mediaDTO = mediaDTO;
	}

	public MediaDTO getBackgroundDTO() {
		return backgroundDTO;
	}

	public void setBackgroundDTO(MediaDTO backgroundDTO) {
		this.backgroundDTO = backgroundDTO;
	}
	
}
