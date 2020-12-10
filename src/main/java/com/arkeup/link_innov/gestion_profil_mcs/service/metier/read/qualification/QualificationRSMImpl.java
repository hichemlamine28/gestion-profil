package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.qualification;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Qualification;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.QualificationRepository;

@Service
public class QualificationRSMImpl implements QualificationRSM {

	@Autowired
	private QualificationRepository qualificationRepository;
	
	@Override
	public Optional<Qualification> getQualification(String id) {
		return qualificationRepository.getQualification(id);
	}

	@Override
	public Page<Qualification> listQualification(String userId, Pageable pageable) {
		return qualificationRepository.listQualification(userId, pageable);
	}

}
