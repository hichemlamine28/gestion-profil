/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ErrorsEnum;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.FunctionalException;
import com.arkeup.link_innov.gestion_profil_mcs.contrainte.errors.ObjetNotFoundException;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Registration;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.inscription.RegistrationDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.RegistrationCUDSM;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.RegistrationRSM;

/**
 * @author mikajy
 *
 */
@Service
public class RegistrationSAImpl implements RegistrationSA {
	
	@Autowired
	private RegistrationRSM registrationRSM;
	
	@Override
	public RegistrationDTO checkAndGetRegistration(String registrationId, String useruid) {
		Registration registration = registrationRSM.findById(registrationId);
		if(registration == null || !registration.getUseruid().equals(useruid)) {
			RegistrationDTO registrationDTO = new RegistrationDTO(false);
			registrationDTO.setError(true);
			registrationDTO.setErrorCode(ErrorsEnum.ERR_MCS_OBJECT_NOT_FOUND.getErrorCode());
			registrationDTO.setErrorMessage(ErrorsEnum.ERR_MCS_OBJECT_NOT_FOUND.getErrorMessage());
			throw new ObjetNotFoundException(registrationDTO, ErrorsEnum.ERR_MCS_OBJECT_NOT_FOUND);
		} else {
			int registrationDurationSec = 24 * 60 * 60;// 24h en sec
			boolean expired = registrationRSM.isExpired(registration, registrationDurationSec);
			if(expired) {
				RegistrationDTO registrationDTO = new RegistrationDTO(false);
				registrationDTO.setError(true);
				registrationDTO.setErrorCode(ErrorsEnum.ERR_MCS_PROFIL_REGISTRATION_EXPIRED.getErrorCode());
				registrationDTO.setErrorMessage(ErrorsEnum.ERR_MCS_PROFIL_REGISTRATION_EXPIRED.getErrorMessage());
				throw new FunctionalException(registrationDTO, ErrorsEnum.ERR_MCS_PROFIL_REGISTRATION_EXPIRED, null);
			}
			return new RegistrationDTO(true, registration.getUseruid(), registration.getEmail());
		} 
	}

}
