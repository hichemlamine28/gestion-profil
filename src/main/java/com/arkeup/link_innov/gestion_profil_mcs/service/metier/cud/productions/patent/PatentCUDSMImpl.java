package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.productions.patent;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Patent;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.PatentRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.PatentMongoRepository;

@Service
public class PatentCUDSMImpl implements PatentCUDSM {

	@Autowired
	private PatentRepository patentRepository;

	@Autowired
	private PatentMongoRepository patentMongoReository;

	@Override
	public Patent createPatent(String ownerId, Patent patent) {
		patent.setId(UUID.randomUUID().toString());
		patent.setOwnerId(ownerId);
		patent.setCreationDate(new Date());
		patent.setModificationDate(new Date());
		return patentRepository.save(patent);
	}

	@Override
	public Patent updatePatent(Patent patent) {
		Optional<Patent> optionalResult = patentMongoReository.findById(patent.getId());
		if (optionalResult.isPresent()) {
			patent.setModificationDate(new Date());
			return patentRepository.save(patent);
		}
		return null;
	}

	@Override
	public void deletePatent(String id) {
		patentRepository.deleteById(id);

	}

}
