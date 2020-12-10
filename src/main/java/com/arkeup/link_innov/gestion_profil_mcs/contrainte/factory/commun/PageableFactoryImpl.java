package com.arkeup.link_innov.gestion_profil_mcs.contrainte.factory.commun;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.utils.HelpPage;
import com.arkeup.link_innov.gestion_profil_mcs.donnee.utils.HelpPageable;


@Component
public class PageableFactoryImpl<T> implements PageableFactory<T> {

	@SuppressWarnings("deprecation")
	@Override
	public Pageable getIstance(HelpPageable helpPageable) {
		return new PageRequest(helpPageable.getPageNumber(),helpPageable.getPageSize());
	}

	@Override
	public HelpPage<T> pageToHelpPage(Page<T> page) {
		return new HelpPage<>(page.getContent(), page.getPageable(),page.getTotalElements());
	}

}
