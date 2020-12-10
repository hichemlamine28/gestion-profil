package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.referential.activitysector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.referential.ActivitySectorMapper;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ActivitySectorsDTO;
import com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.referential.activitysector.ActivitySectorRSM;

@Service
public class ActivitySectorRSAImpl implements ActivitySectorRSA {

	@Autowired
	ActivitySectorRSM activitySectorRSM;
	
	@Autowired
	ActivitySectorMapper activitySectorFactory;
	
	@Override
	public ActivitySectorsDTO listActivitySectors(Pageable pageable) {
		
		ActivitySectorsDTO result = new ActivitySectorsDTO();
		result.setActivitySectorsDTO(activitySectorFactory.activitySectorPageToActivitySectorDTOPage(activitySectorRSM.listActivitySectors(pageable), pageable));
		result.setError(false);
		result.setMessage("List of all activity sectors");
		return result;
	}

}
