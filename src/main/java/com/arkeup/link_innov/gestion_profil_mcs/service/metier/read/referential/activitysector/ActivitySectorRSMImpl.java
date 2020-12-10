package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.referential.activitysector;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.ActivitySector;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ActivitySectorRepository;

@Service
public class ActivitySectorRSMImpl implements ActivitySectorRSM {

	@Autowired
	ActivitySectorRepository activitySectorRepository;
	
	@Override
	public Page<ActivitySector> listActivitySectors(Pageable pageable) {
		return activitySectorRepository.findAll(pageable);
	}

	@Override
	public Optional<ActivitySector> findById(String id) {
		return activitySectorRepository.findById(id);
	}

}
