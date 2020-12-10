package com.arkeup.link_innov.gestion_profil_mcs.service.applicatif.cud.referential.typology;

import com.arkeup.link_innov.gestion_profil_mcs.donnee.dto.referential.TypologyDTO;

public interface TypologyCUDSA {

	public TypologyDTO addTypology(TypologyDTO typologyDTO);

	public TypologyDTO updateTypology(TypologyDTO typologysDTO);

	public TypologyDTO deleteTypology(String idTypology);

	public void initDatabase();

}
