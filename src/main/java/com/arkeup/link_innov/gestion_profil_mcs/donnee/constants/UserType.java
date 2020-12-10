package com.arkeup.link_innov.gestion_profil_mcs.donnee.constants;

/**
 * 
 * @author Jéremy
 *
 */
public enum UserType {
	ACADEMIC("Académique"),
	INDUSTRIAL("Industriel"),
	OTHER("Autre acteur de l'innovation");
	
	private String value;
	
	UserType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public static UserType fromString(String text) {
		if (text != null) {
			for (UserType userType : UserType.values()) {
				if (text.equalsIgnoreCase(userType.value)) {
					return userType;
				}
			}
		}
		return null;
	}
}