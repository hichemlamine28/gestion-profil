/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.donnee.constants;

/**
 * @author mikajy
 *
 */
public enum RoleEnum {
	
	USER("perm_user"),
	ADMIN("perm_admin"),
	ACTIF("status_actif"),
	INACTIF("status_inactif"),
	BLOCKED("status_blocked"),
	
	FREEMIUM("ab_freemium"),
	PREMIUM("ab_premium");
	
	private String value;
	
	RoleEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	public static RoleEnum fromString(String text) {
		if (text != null) {
			for (RoleEnum role : RoleEnum.values()) {
				if (text.equalsIgnoreCase(role.value)) {
					return role;
				}
			}
		}
		return null;
	}
}
