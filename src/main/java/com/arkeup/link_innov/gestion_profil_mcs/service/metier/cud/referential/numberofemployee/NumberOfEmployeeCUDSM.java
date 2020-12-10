package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.referential.numberofemployee;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.NumberOfEmployee;

public interface NumberOfEmployeeCUDSM {
	
	public NumberOfEmployee create(NumberOfEmployee numberOfEmployee);
	public void update(NumberOfEmployee numberOfEmployee);
	public void delete(String id);
	
}
