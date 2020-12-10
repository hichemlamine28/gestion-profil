package com.arkeup.link_innov.gestion_profil_mcs.donnee.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * @author BGH
 *
 */
@Document
public class UserConnexion {
	
	@Id
    private String id;
	
	private Date newConnexion;
	
	private Date oldConnexion;
	
	private String username;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getNewConnexion() {
		return newConnexion;
	}

	public void setNewConnexion(Date newConnexion) {
		this.newConnexion = newConnexion;
	}

	public Date getOldConnexion() {
		return oldConnexion;
	}

	public void setOldConnexion(Date oldConnexion) {
		this.oldConnexion = oldConnexion;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


}
