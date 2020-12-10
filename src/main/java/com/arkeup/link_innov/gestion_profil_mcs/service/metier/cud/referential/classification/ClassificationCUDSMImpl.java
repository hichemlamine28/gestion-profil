package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.referential.classification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Classification;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ClassificationRepository;

@Service
public class ClassificationCUDSMImpl implements ClassificationCUDSM {

	@Autowired
	private ClassificationRepository classificationRepository;
	
	@Override
	public Classification create(Classification classification) {
		classificationRepository.save(classification);
		return classification;
	}

	@Override
	public void update(Classification classification) {	
		classificationRepository.save(classification);
	}

	@Override
	public void delete(String id) {
		classificationRepository.deleteById(id);
		
	}

}
