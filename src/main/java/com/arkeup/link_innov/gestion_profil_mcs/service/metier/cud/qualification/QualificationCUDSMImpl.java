package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.qualification;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Qualification;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.QualificationRepository;

@Service
public class QualificationCUDSMImpl implements QualificationCUDSM {
	
	@Autowired
	private QualificationRepository qualificationRepository;

	@Override
	public Qualification create(Qualification qualification) {
		qualification.createDate = new Date();
		qualificationRepository.save(qualification);
		return qualification;
	}

	@Override
	public void update(Qualification qualification) {
		qualification.modifDate = new Date();
		qualificationRepository.save(qualification);
	}

	@Override
	public Optional<Qualification> delete(String id) {
		Optional<Qualification> result = qualificationRepository.findById(id);
		if (result.isPresent()) {
			qualificationRepository.delete(result.get());
		}	
		return result;
	}
	
	

}
