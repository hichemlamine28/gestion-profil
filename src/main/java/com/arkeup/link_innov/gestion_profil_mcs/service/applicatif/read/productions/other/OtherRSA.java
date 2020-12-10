package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.read.productions.other;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.OtherProductionDTO;

public interface OtherRSA {

	public Page<OtherProductionDTO> getByOwnerId(String ownerId, Pageable pageable);
	
	public OtherProductionDTO getById(String id);

	public int getOtherProductionByOwnerId(String ownerId);
}
