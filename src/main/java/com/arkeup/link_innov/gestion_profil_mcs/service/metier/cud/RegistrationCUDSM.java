/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud;

import java.util.Date;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Registration;

/**
 * @author mikajy
 *
 */
public interface RegistrationCUDSM {
	
	/**
	 * 
	 * @param registration
	 * @return
	 */
	public Registration save(Registration registration);
	
	/**
	 * 
	 * @param refDate
	 * @param timeoutInSecond
	 */
	public void deleteExpiredRegistrations(Date refDate, long timeoutInSecond);
	
	/**
	 * 
	 * @param registrationId
	 */
	public void deleteByUseruid(String useruid);
	
}
