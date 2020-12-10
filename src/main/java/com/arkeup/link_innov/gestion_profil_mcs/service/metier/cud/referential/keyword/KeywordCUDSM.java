package com.arkeup.link_innov.gestion_profil_mcs.service.metier.cud.referential.keyword;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.domain.referential.Keyword;

public interface KeywordCUDSM {

	public Keyword mongoCreate(Keyword keyword);

	public Keyword esCreate(Keyword keyword);

	public Keyword update(Keyword keyword);

	public void delete(String id);

	public Keyword mongoUpdate(Keyword keyword);

}
