package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.productions.patent;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Patent;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.PatentRepository;

@Service
public class PatentRSMImpl implements PatentRSM {

	@Autowired
	private PatentRepository patentRepository;

	@Override
	public Page<Patent> getByOwnerId(String ownerId, Pageable pageable) {
		return patentRepository.findByOwnerId(ownerId, pageable);
	}

	@Override
	public Patent getById(String patentId) {
		Optional<Patent> patent = patentRepository.findById(patentId);
		return (patent.isPresent()) ? patent.get() : null;
	}

	@Override
	public int getNumberPatentByOwnerId(String ownerId) {
		return patentRepository.findByOwnerId(ownerId).size();
	}

	@Override
	public List<Patent> findAllByOwnerId(String ownerId) {
		return patentRepository.findByOwnerId(ownerId);
	}

	@Override
	public Boolean publicationNumberIsAlreadyExist(String publicationNumber, String ownerId) {
		Boolean isExist = false;
		for (Patent patent : patentRepository.findByPublicationNumber(publicationNumber)) {
			if (patent.getOwnerId().equals(ownerId)) {
				isExist = true;
				break;
			}
		}
		return isExist;
//		return patentRepository.findByPublicationNumber(publicationNumber).size() > 0;
	}

}
