/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Registration;

/**
 * @author mikajy
 *
 */
public interface RegistrationFactory {
	
	Registration getEntityInstance();
	
	Registration getEntityInstance(String useruid);
	
	Registration getEntityInstance(String useruid, String email);
}
