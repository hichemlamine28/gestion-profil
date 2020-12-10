package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.productions.other;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.OtherProductionDTO;

public interface OtherCUDSA {

	public OtherProductionDTO createOtherProduction(String ownerId, OtherProductionDTO otherProductionDTO);

	public OtherProductionDTO updateOtherProduction(OtherProductionDTO otherProductionDTO);

	public OtherProductionDTO deleteOtherProduction(String id);

}
