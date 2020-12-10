package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.referential.activitysector;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.ActivitySector;

public interface ActivitySectorCUDSM {
	
	public ActivitySector create(ActivitySector activitySector);
	public void update(ActivitySector activitySector);
	public void delete(String id);
}
