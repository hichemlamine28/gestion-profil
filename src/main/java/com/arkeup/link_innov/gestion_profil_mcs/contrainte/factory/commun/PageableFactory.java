package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.commun;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.utils.HelpPage;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.utils.HelpPageable;

public interface PageableFactory<T> {
	
	public Pageable getIstance(HelpPageable helpPageable);
	
	public HelpPage<T> pageToHelpPage(Page<T> page);

}
