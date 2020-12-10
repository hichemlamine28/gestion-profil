package com.arkeup.link_innov.gestion_profil_mcs.service.metier.read.information;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserAuth;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.UserInformation;

public interface UserInformationRSM {

	UserInformation getInformation(String username);
	
	UserAuth getUserByPseudo(String pseudoName);

}
