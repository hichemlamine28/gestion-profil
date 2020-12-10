package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.referential.classification;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Classification;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ClassificationRepository;

@Service
public class ClassificationRSMImpl implements ClassificationRSM {

	@Autowired
	ClassificationRepository classificationRepository;
	
	@Override
	public Page<Classification> listClassifications(Pageable pageable) {
		return classificationRepository.findAll(pageable);
	}

	@Override
	public Optional<Classification> findById(String id) {
		return classificationRepository.findById(id);
	}
	
	

}
