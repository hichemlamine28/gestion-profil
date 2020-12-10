/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory;

import org.springframework.stereotype.Component;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Registration;

/**
 * @author mikajy
 *
 */
@Component
public class RegistrationFactoryImpl implements RegistrationFactory {

	/* (non-Javadoc)
	 * @see com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.RegistrationFactory#getEntityInstance()
	 */
	@Override
	public Registration getEntityInstance() {
		return new Registration();
	}

	/* (non-Javadoc)
	 * @see com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.RegistrationFactory#getEntityInstance(java.lang.String, java.lang.String)
	 */
	@Override
	public Registration getEntityInstance(String useruid) {
		Registration registration = new Registration();
		registration.setUseruid(useruid);
//		registration.setProfilId(profilId);
		return registration;
	}

	@Override
	public Registration getEntityInstance(String useruid, String email) {
		Registration registration = new Registration();
		registration.setUseruid(useruid);
		registration.setEmail(email);
		return registration;
	}

}
