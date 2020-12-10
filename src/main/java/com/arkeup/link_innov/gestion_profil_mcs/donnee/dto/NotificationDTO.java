package com.arkeup.link_innov.gestion_profil_mcs.donnee.dto;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.commun.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class NotificationDTO extends BaseDTO{
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String id;	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String message;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Boolean isReceived;
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
	
	@Override
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Boolean getIsReceived() {
		return isReceived;
	}
	
	public void setIsReceived(Boolean isReceived) {
		this.isReceived = isReceived;
	}
	
	@Override
	public String toString() {
		return "NotificationDTO [id=" + id + ", message=" + message + ", isReceived=" + isReceived + "]";
	}
	
	public NotificationDTO() {
		super();
	}
	
	public NotificationDTO(String id, String message) {
		super();
		this.id = id;
		this.message = message;
	}
	

}
