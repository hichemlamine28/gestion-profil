package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.referential.activitysector;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.ActivitySector;

public interface ActivitySectorRSM {
	Page<ActivitySector> listActivitySectors(Pageable pageable);
	Optional<ActivitySector> findById(String id);
}
