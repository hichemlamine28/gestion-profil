package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.information;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserInformation;

public interface UserInformationCUDSM {
	
	public UserInformation create(UserInformation userInformation);
	public void update(UserInformation userInformation);
	
}
