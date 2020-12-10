/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory;

import org.springframework.stereotype.Component;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.businessdelegate.MailParametersDTO;

/**
 * @author mikajy
 *
 */
@Component
public class MailParametersDTOFactoryImpl implements MailParametersDTOFactory {

	@Override
	public MailParametersDTO getInstance() {
		return new MailParametersDTO();
	}

	@Override
	public MailParametersDTO getInstance(int type, String language, String mail, String registrationId, String userName,
			String firstName) {
		MailParametersDTO mailParametersDTO = new MailParametersDTO();
		// type = 1 if new account creation and = 2 when password changing
		mailParametersDTO.setType(type);
		mailParametersDTO.setLanguage(language);
		mailParametersDTO.setUserName(userName);
		mailParametersDTO.setRegistrationId(registrationId);
		mailParametersDTO.setMail(mail);
		mailParametersDTO.setFirstName(firstName);
		return mailParametersDTO;
	}

	@Override
	public MailParametersDTO getInstance(int type, String language, String mail, String registrationId, String userName,
			String firstName, String keyValidateProfil) {
		MailParametersDTO mailParametersDTO = new MailParametersDTO();
		// type = 1 if new account creation and = 2 when password changing
		mailParametersDTO.setType(type);
		mailParametersDTO.setLanguage(language);
		mailParametersDTO.setUserName(userName);
		mailParametersDTO.setRegistrationId(registrationId);
		mailParametersDTO.setMail(mail);
		mailParametersDTO.setFirstName(firstName);
		mailParametersDTO.setKeyValidateProfil(keyValidateProfil);
		return mailParametersDTO;
	}

}
