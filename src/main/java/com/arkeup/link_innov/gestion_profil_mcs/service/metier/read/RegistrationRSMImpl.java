/**
 * 
 */
package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Registration;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.RegistrationMongoRepository;

/**
 * @author mikajy
 *
 */
@Service
public class RegistrationRSMImpl implements RegistrationRSM {
	
	@Autowired
	private RegistrationMongoRepository registrationMongoRepository;

	/* (non-Javadoc)
	 * @see com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.RegistrationRSM#findByIdAndUsername(java.lang.String, java.lang.String)
	 */
	@Override
	public Registration findByIdAndUseruid(String id, String useruid) {
		Registration registration = registrationMongoRepository.findByIdAndUseruid(id, useruid);
		return registration;
	}

	@Override
	public boolean isExpired(Registration registration, int delayInSecond) {
		Date now = new Date(Instant.now().atZone(ZoneOffset.UTC).toInstant().toEpochMilli());
		
		Calendar expirationDate = Calendar.getInstance(TimeZone.getTimeZone(ZoneOffset.UTC));
		expirationDate.setTime(registration.getCreationDate());
		expirationDate.add(Calendar.SECOND, delayInSecond);
		return now.after(expirationDate.getTime());
	}

	@Override
	public Registration findById(String registrationId) {
		Optional<Registration> optional = registrationMongoRepository.findById(registrationId);
		return optional.isPresent() ? optional.get() : null;
	}

}
