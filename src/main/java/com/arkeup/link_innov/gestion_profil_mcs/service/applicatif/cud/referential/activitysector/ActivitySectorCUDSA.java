package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.referential.activitysector;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ActivitySectorDTO;

public interface ActivitySectorCUDSA {

	public ActivitySectorDTO addActivitySector(ActivitySectorDTO activitySectorDTO);

	public ActivitySectorDTO updateActivitySector(ActivitySectorDTO activitySectorsDTO);

	public ActivitySectorDTO deleteActivitySector(String idActivitySector);

	public void initDatabase();

}
