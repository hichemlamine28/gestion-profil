/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.inscription.RegistrationDTO;

/**
 * @author mikajy
 *
 */
public interface RegistrationSA {
	
	/**
	 * Verifier si une inscription est valide ou pas.
	 * Une inscription est valide si.
	 * 1. registrationId et profilId correspondent à un objet Registration
	 * 2. l'inscription n'a pas encore expiré
	 * 
	 * @param registrationId
	 * @param profilId
	 * @return RegistrationDTO
	 */
	RegistrationDTO checkAndGetRegistration(String registrationId, String profilId);
}
