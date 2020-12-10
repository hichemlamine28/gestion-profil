package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.productions.other;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.OtherProduction;

public interface OtherRSM {

	public Page<OtherProduction> getByOwnerId(String ownerId, Pageable pageable);
	
	public OtherProduction getById(String id);

	public int getOtherProductionByOwnerId(String ownerId);

}
