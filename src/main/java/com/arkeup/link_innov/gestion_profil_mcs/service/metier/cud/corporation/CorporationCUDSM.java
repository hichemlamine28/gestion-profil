package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.corporation;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Corporation;

import java.util.List;

public interface CorporationCUDSM {

	public Corporation create(Corporation corporation);

	public Corporation update(Corporation corporation);

	public void deleteCorporation(String corporationId);

	void delete(Corporation corporation);

	List<Corporation> updateAll(List<Corporation> corporations);
}
