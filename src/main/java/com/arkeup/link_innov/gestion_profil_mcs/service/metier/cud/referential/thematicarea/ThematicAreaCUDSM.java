package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.referential.thematicarea;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.ThematicArea;

public interface ThematicAreaCUDSM {
	
	public ThematicArea create(ThematicArea thematicArea);
	public void update(ThematicArea thematicArea);
	public void delete(String id);
	
}
