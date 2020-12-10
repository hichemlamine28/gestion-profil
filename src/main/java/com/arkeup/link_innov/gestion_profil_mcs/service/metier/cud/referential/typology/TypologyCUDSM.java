package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.referential.typology;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Typology;

public interface TypologyCUDSM {
	
	public Typology create(Typology typology);
	public void update(Typology typology);
	public void delete(String id);
	
}
