package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.productions.patent;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.PatentDTO;

public interface PatentCUDSA {

	public PatentDTO createPatent(String ownerId, PatentDTO patentDTO);

	public PatentDTO updatePatent(PatentDTO patentDTO);

	public PatentDTO deletePatent(String id);

}
