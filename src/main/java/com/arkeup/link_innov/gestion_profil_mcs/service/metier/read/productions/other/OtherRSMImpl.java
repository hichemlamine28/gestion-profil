package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.productions.other;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.OtherProduction;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.OtherProductionRepository;

@Service
public class OtherRSMImpl implements OtherRSM {

	@Autowired
	private OtherProductionRepository otherProductionRepository;

	@Override
	public Page<OtherProduction> getByOwnerId(String ownerId, Pageable pageable) {
		return otherProductionRepository.findByOwnerId(ownerId, pageable);
	}

	@Override
	public OtherProduction getById(String id) {
		Optional<OtherProduction> other = otherProductionRepository.findById(id);
		
		return (other.isPresent()) ? other.get() : null;
	}

	@Override
	public int getOtherProductionByOwnerId(String ownerId) {
		return otherProductionRepository.findByOwnerId(ownerId).size();
	}

}
