/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Registration;

/**
 * @author mikajy
 *
 */
public interface RegistrationRSM {
	
	/**
	 * 
	 * @param id
	 * @param username
	 * @return
	 */
	Registration findByIdAndUseruid(String id, String username);
	
	/**
	 * 
	 * @param id
	 * @param profilId
	 * @return
	 */
	Registration findById(String registrationId);
	
	/**
	 * Check if registration has expired or not.
	 * A registration expired when {@link Registration#getCreationDate()} < 24h from now. 
	 * 
	 * @param registration
	 * @return
	 */
	boolean isExpired(Registration registration, int delayInSecond);
}
