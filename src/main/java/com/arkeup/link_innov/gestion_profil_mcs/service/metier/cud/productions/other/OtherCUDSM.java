package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.productions.other;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.OtherProduction;

public interface OtherCUDSM {

	public OtherProduction createOtherProduction(String ownerId, OtherProduction otherProduction);

	public OtherProduction updateOtherProduction(OtherProduction otherProduction);

	public void deleteOtherProduction(String id);
}
