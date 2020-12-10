/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Registration;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.RegistrationMongoRepository;
import org.springframework.util.StringUtils;

/**
 * @author mikajy
 *
 */
@Service
public class RegistrationCUDSMImpl implements RegistrationCUDSM {
	
	@Autowired
	private RegistrationMongoRepository registrationMongoRepository;

	/* (non-Javadoc)
	 * @see com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.RegistrationCUDSM#save(com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Registration)
	 */
	@Override
	public Registration save(Registration registration) {
		Registration registrationOriginal = registrationMongoRepository.findOneByUseruid(registration.getUseruid());
		if(registrationOriginal != null && !StringUtils.isEmpty(registrationOriginal.getId()))
			registration.setId(registrationOriginal.getId());
		else
			registration.setId(UUID.randomUUID().toString());

		Date creationDate = new Date(Instant.now().atZone(ZoneOffset.UTC).toInstant().toEpochMilli());
		registration.setCreationDate(creationDate);
		return registrationMongoRepository.save(registration);
	}

	/* (non-Javadoc)
	 * @see com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.RegistrationCUDSM#deleteExpiredRegistrations(java.util.Date)
	 */
	@Override
	public void deleteExpiredRegistrations(Date refDate, long timeoutInSecond) {
		throw new NotImplementedException("");
	}

	@Override
	public void deleteByUseruid(String useruid) {
		if(useruid == null) return ;
		Registration reg = registrationMongoRepository.findOneByUseruid(useruid);
		if(reg != null) {
			registrationMongoRepository.delete(reg);
		}
	}

}
