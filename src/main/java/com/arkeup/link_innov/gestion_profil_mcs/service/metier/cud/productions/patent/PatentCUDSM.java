package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.productions.patent;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Patent;

public interface PatentCUDSM {

	public Patent createPatent(String ownerId, Patent patent);

	public Patent updatePatent(Patent patent);

	public void deletePatent(String id);
}
