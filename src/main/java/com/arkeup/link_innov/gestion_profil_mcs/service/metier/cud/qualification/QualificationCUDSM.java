package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.qualification;

import java.util.Optional;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.Qualification;

public interface QualificationCUDSM {
	public Qualification create(Qualification qualification);
	public void update(Qualification qualification);
	public Optional<Qualification> delete(String id);
}
