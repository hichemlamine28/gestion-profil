package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.referential.activitysector;

import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.ActivitySectorsDTO;

public interface ActivitySectorRSA {
	ActivitySectorsDTO listActivitySectors(Pageable pageable);
}
