package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.productions.other;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.OtherProduction;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.OtherProductionRepository;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.mongo.mcs.OtherProductionMongoRepository;

@Service
public class OtherCUDSMImpl implements OtherCUDSM {

	@Autowired
	private OtherProductionRepository otherProductionRepository;

	@Autowired
	private OtherProductionMongoRepository otherProductionMongoReository;

	@Override
	public OtherProduction createOtherProduction(String ownerId, OtherProduction otherProduction) {
		otherProduction.setId(UUID.randomUUID().toString());
		otherProduction.setOwnerId(ownerId);
		otherProduction.setCreationDate(new Date());
		otherProduction.setModificationDate(new Date());
		return otherProductionRepository.save(otherProduction);
	}

	@Override
	public OtherProduction updateOtherProduction(OtherProduction otherProduction) {
		Optional<OtherProduction> optionalResult = otherProductionMongoReository.findById(otherProduction.getId());
		if (optionalResult.isPresent()) {
			otherProduction.setModificationDate(new Date());
			return otherProductionRepository.save(otherProduction);
		}
		return null;
	}

	@Override
	public void deleteOtherProduction(String id) {
		otherProductionRepository.deleteById(id);
	}

}
