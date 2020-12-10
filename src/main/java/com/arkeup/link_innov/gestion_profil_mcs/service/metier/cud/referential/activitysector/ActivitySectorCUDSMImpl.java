package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.referential.activitysector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.ActivitySector;
import com.arkeup.link_innov.gestion_profil_mcs.service.repository.ActivitySectorRepository;

@Service
public class ActivitySectorCUDSMImpl implements ActivitySectorCUDSM {

	@Autowired
	private ActivitySectorRepository activitySectorRepository;
	
	@Override
	public ActivitySector create(ActivitySector activitySector) {
		activitySectorRepository.save(activitySector);
		return activitySector;
	}

	@Override
	public void update(ActivitySector activitySector) {
		activitySectorRepository.save(activitySector);
	}

	@Override
	public void delete(String id) {
		activitySectorRepository.deleteById(id);
		
	}

}
