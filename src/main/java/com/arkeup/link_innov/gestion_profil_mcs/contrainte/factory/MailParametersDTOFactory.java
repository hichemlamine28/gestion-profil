/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.MailParametersDTO;

/**
 * @author mikajy
 *
 */
public interface MailParametersDTOFactory {
	/**
	 * 
	 * @return
	 */
	MailParametersDTO getInstance();
	
	/**
	 * 
	 * @param type
	 * @param languages
	 * @return
	 */
	MailParametersDTO getInstance(int type, String language, String userName, String registrationId, String profileId, String lastName);

	MailParametersDTO getInstance(int type, String language, String userName, String registrationId, String profileId, String lastName,String keyValidateProfil);
}
