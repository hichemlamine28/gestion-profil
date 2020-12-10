package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.referential.thematicarea;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.ThematicArea;

public interface ThematicAreaRSM {
	Page<ThematicArea> listThematicAreas(Pageable pageable);
	Optional<ThematicArea> findById(String id);
}
