package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.referential.classification;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Classification;

public interface ClassificationCUDSM {
	
	public Classification create(Classification classification);
	public void update(Classification classification);
	public void delete(String id);
	
}
